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
package org.broadleafcommerce.cms.structure.service;

import org.broadleafcommerce.cms.structure.domain.StructuredContent;
import org.broadleafcommerce.common.extension.AbstractExtensionHandler;
import org.broadleafcommerce.common.extension.ExtensionResultHolder;
import org.broadleafcommerce.common.extension.ExtensionResultStatusType;
import org.broadleafcommerce.common.structure.dto.StructuredContentDTO;

import java.util.List;

/**
 * Extension handler for the {@link StructuredContentService}
 *
 * @author bpolster
 */
public class AbstractStructuredContentServiceExtensionHandler extends AbstractExtensionHandler
        implements StructuredContentServiceExtensionHandler {

    public ExtensionResultStatusType populateAdditionalStructuredContentFields(StructuredContent sc,
            StructuredContentDTO dto, boolean secure) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }


    public ExtensionResultStatusType modifyStructuredContentDtoList(List<StructuredContentDTO> structuredContentList,
            ExtensionResultHolder resultHolder) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }
}
