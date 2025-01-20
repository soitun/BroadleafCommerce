/*-
 * #%L
 * BroadleafCommerce CMS Module
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
package org.broadleafcommerce.cms.url.dao;

import org.broadleafcommerce.cms.url.domain.URLHandler;

import java.util.List;


/**
 * Created by ppatel.
 */
public interface URLHandlerDao {


    public URLHandler findURLHandlerByURI(String uri);

    /**
     * Gets all the URL handlers configured in the system
     *
     * @return
     */
    public List<URLHandler> findAllURLHandlers();

    public URLHandler saveURLHandler(URLHandler handler);

    public URLHandler findURLHandlerById(Long id);

    public List<URLHandler> findAllRegexURLHandlers();

}
