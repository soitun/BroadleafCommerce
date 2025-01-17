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
package org.broadleafcommerce.common.cache;

/**
 * Represents a block of work to execute during a call to
 * {@link org.broadleafcommerce.common.cache.AbstractCacheMissAware#getCachedObject(Class, String, String, PersistentRetrieval, String...)}
 * should a missed cache item not be detected. Should return an instance of the cache miss item type retrieved
 * from the persistent store.
 *
 * @author Jeff Fischer
 * @see org.broadleafcommerce.common.cache.AbstractCacheMissAware
 */
public interface PersistentRetrieval<T> {

    T retrievePersistentObject();

}
