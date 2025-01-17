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
package org.broadleafcommerce.common.web.exception;

import java.io.Serial;

public class HaltFilterChainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4138837912315748276L;

    // for serialization purposes
    protected HaltFilterChainException() {
        super();
    }

    public HaltFilterChainException(String message, Throwable cause) {
        super(message, cause);
    }

    public HaltFilterChainException(String message) {
        super(message);
    }

    public HaltFilterChainException(Throwable cause) {
        super(cause);
    }

}
