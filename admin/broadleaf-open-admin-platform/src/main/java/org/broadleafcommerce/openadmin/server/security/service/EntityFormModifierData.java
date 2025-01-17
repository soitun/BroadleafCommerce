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
package org.broadleafcommerce.openadmin.server.security.service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Hold generic key/value data for configuring a {@link EntityFormModifier}, along with a modifierType value
 * that should be used to qualify EntityFormModifier instances capable of consuming this configuration.
 *
 * @author Jeff Fischer
 */
public class EntityFormModifierData<T extends EntityFormModifierDataPoint> extends ArrayList<T> {

    protected String modifierType;

    public EntityFormModifierData(int initialCapacity) {
        super(initialCapacity);
    }

    public EntityFormModifierData() {
    }

    public EntityFormModifierData(Collection<? extends T> c) {
        super(c);
    }

    public String getModifierType() {
        return modifierType;
    }

    public void setModifierType(String modifierType) {
        this.modifierType = modifierType;
    }

}
