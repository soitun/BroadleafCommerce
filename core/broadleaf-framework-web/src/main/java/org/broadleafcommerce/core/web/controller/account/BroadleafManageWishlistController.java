/*-
 * #%L
 * BroadleafCommerce Framework Web
 * %%
 * Copyright (C) 2009 - 2025 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.core.web.controller.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.core.catalog.domain.Sku;
import org.broadleafcommerce.core.inventory.service.ContextualInventoryService;
import org.broadleafcommerce.core.inventory.service.InventoryUnavailableException;
import org.broadleafcommerce.core.order.domain.BundleOrderItem;
import org.broadleafcommerce.core.order.domain.DiscreteOrderItem;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.order.service.call.OrderItemRequestDTO;
import org.broadleafcommerce.core.order.service.exception.AddToCartException;
import org.broadleafcommerce.core.order.service.exception.RemoveFromCartException;
import org.broadleafcommerce.core.order.service.exception.UpdateCartException;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The controller responsible for wishlist management activities, including
 * viewing a wishlist, moving items from the wishlist to the cart, and
 * removing items from the wishlist
 *
 * @author jfridye
 */
public class BroadleafManageWishlistController extends AbstractAccountController {

    private static final Log LOG = LogFactory.getLog(BroadleafManageWishlistController.class);
    protected static String accountWishlistView = "account/manageWishlist";
    protected static String accountWishlistRedirect = "redirect:/account/wishlist";

    @Resource(name = "blInventoryService")
    protected ContextualInventoryService inventoryService;

    public String add(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            OrderItemRequestDTO itemRequest,
            String wishlistName
    ) throws IOException, AddToCartException, PricingException {
        Order wishlist = orderService.findNamedOrderForCustomer(wishlistName, CustomerState.getCustomer(request));

        if (wishlist == null) {
            wishlist = orderService.createNamedOrderForCustomer(wishlistName, CustomerState.getCustomer(request));
        }

        wishlist = orderService.addItem(wishlist.getId(), itemRequest, false);
        wishlist = orderService.save(wishlist, true);

        return getAccountWishlistRedirect();
    }

    public String viewWishlist(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            String wishlistName
    ) {
        Order wishlist = orderService.findNamedOrderForCustomer(wishlistName, CustomerState.getCustomer());
        model.addAttribute("wishlist", wishlist);
        return getAccountWishlistView();
    }

    public String updateQuantityInWishlist(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            String wishlistName,
            OrderItemRequestDTO itemRequest
    ) throws IOException, UpdateCartException, PricingException, RemoveFromCartException {
        Order wishlist = orderService.findNamedOrderForCustomer(wishlistName, CustomerState.getCustomer());

        wishlist = orderService.updateItemQuantity(wishlist.getId(), itemRequest, true);
        wishlist = orderService.save(wishlist, false);

        model.addAttribute("wishlist", wishlist);
        return getAccountWishlistView();
    }

    public String removeItemFromWishlist(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            String wishlistName,
            Long itemId
    ) throws RemoveFromCartException {
        Order wishlist = orderService.findNamedOrderForCustomer(wishlistName, CustomerState.getCustomer());

        orderService.removeItem(wishlist.getId(), itemId, false);

        model.addAttribute("wishlist", wishlist);
        return getAccountWishlistRedirect();
    }

    public String moveItemToCart(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            String wishlistName,
            Long orderItemId
    ) throws RemoveFromCartException, AddToCartException, PricingException {
        Order wishlist = orderService.findNamedOrderForCustomer(wishlistName, CustomerState.getCustomer());
        if (!isWishlistValid(wishlist)) {
            model.addAttribute("wishlist", wishlist);
            model.addAttribute("invalidWishlist", true);
            return getAccountWishlistView();
        }
        List<OrderItem> orderItems = wishlist.getOrderItems();

        OrderItem orderItem = null;
        for (OrderItem item : orderItems) {
            if (orderItemId.equals(item.getId())) {
                orderItem = item;
                break;
            }
        }

        if (orderItem != null) {
            Order cartOrder = orderService.addItemFromNamedOrder(wishlist, orderItem, false);
            cartOrder = orderService.save(cartOrder, true);
        } else {
            throw new IllegalArgumentException("The item id provided was not found in the wishlist");
        }

        model.addAttribute("wishlist", wishlist);

        return getAccountWishlistRedirect();
    }

    public String moveListToCart(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            String wishlistName
    ) throws RemoveFromCartException, AddToCartException, PricingException {
        Order wishlist = orderService.findNamedOrderForCustomer(wishlistName, CustomerState.getCustomer());
        if (!isWishlistValid(wishlist)) {
            model.addAttribute("wishlist", wishlist);
            model.addAttribute("invalidWishlist", true);
            return getAccountWishlistView();
        }
        Order cartOrder = orderService.addAllItemsFromNamedOrder(wishlist, false);
        cartOrder = orderService.save(cartOrder, true);
        model.addAttribute("wishlist", wishlist);
        return getAccountWishlistRedirect();
    }

    protected boolean isWishlistValid(Order wishlist) {
        try {
            Map<Sku, Integer> skuItems = new HashMap<>();
            for (OrderItem orderItem : wishlist.getOrderItems()) {
                Sku sku;
                if (orderItem instanceof DiscreteOrderItem) {
                    sku = ((DiscreteOrderItem) orderItem).getSku();
                } else if (orderItem instanceof BundleOrderItem) {
                    sku = ((BundleOrderItem) orderItem).getSku();
                } else {
                    final String message = "Could not check availability; did not recognize passed-in item "
                            + orderItem.getClass().getName();
                    LOG.warn(message);
                    return false;
                }
                if (!sku.isActive()) {
                    return false;
                }
                skuItems.merge(sku, orderItem.getQuantity(), (oldVal, newVal) -> oldVal + newVal);
            }
            for (Map.Entry<Sku, Integer> entry : skuItems.entrySet()) {
                inventoryService.checkSkuAvailability(wishlist, entry.getKey(), entry.getValue());
            }
        } catch (InventoryUnavailableException e) {
            return false;
        }
        return true;
    }

    public String getAccountWishlistView() {
        return accountWishlistView;
    }

    public String getAccountWishlistRedirect() {
        return accountWishlistRedirect;
    }

}
