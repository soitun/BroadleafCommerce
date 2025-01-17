/*-
 * #%L
 * BroadleafCommerce Common Libraries
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
package org.broadleafcommerce.common.payment.service;

import org.broadleafcommerce.common.payment.PaymentGatewayType;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Elbert Bautista (elbertbautista)
 */
public interface PaymentGatewayResolver {

    /**
     * Used by Transparent Redirect Solutions that utilize Thymeleaf Processors and Expressions.
     * This method should determine whether or not an extension handler should run for a particular gateway.
     *
     * @param handlerType
     * @return
     */
    boolean isHandlerCompatible(PaymentGatewayType handlerType);

    /**
     * Resolves a {@link org.broadleafcommerce.common.payment.PaymentGatewayType}
     * based on a {@link org.springframework.web.context.request.WebRequest}
     *
     * @param request
     * @return
     */
    PaymentGatewayType resolvePaymentGateway(WebRequest request);

}
