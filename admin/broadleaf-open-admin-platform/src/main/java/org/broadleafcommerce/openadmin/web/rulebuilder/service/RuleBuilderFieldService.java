/*-
 * #%L
 * BroadleafCommerce Open Admin Platform
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
package org.broadleafcommerce.openadmin.web.rulebuilder.service;

import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.openadmin.web.rulebuilder.dto.FieldDTO;
import org.broadleafcommerce.openadmin.web.rulebuilder.dto.FieldData;
import org.broadleafcommerce.openadmin.web.rulebuilder.dto.FieldWrapper;

import java.util.List;

/**
 * @author Elbert Bautista (elbertbautista)
 */
public interface RuleBuilderFieldService extends Cloneable {

    String getName();

    FieldWrapper buildFields();

    FieldDTO getField(String fieldName);

    SupportedFieldType getSupportedFieldType(String fieldName);

    SupportedFieldType getSecondaryFieldType(String fieldName);

    List<FieldData> getFields();

    void setFields(List<FieldData> fields);

    String getOverrideFieldEntityKey(String fieldName);

    RuleBuilderFieldService clone() throws CloneNotSupportedException;

    void setRuleBuilderFieldServiceExtensionManager(RuleBuilderFieldServiceExtensionManager extensionManager);

}
