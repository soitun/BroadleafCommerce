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
package org.broadleafcommerce.openadmin.server.service.artifact.image.effects.chain.conversion.impl;

import org.broadleafcommerce.openadmin.server.service.artifact.image.effects.chain.conversion.ConversionException;
import org.broadleafcommerce.openadmin.server.service.artifact.image.effects.chain.conversion.Parameter;
import org.broadleafcommerce.openadmin.server.service.artifact.image.effects.chain.conversion.ParameterConverter;

import java.awt.*;
import java.util.StringTokenizer;

public class RectangleParameterConverter implements ParameterConverter {

    /* (non-Javadoc)
     * @see com.xpressdocs.email.asset.effects.chain.conversion.ParameterConverter#convert(java.lang.String, double)
     */
    public Parameter convert(String value, Double factor, boolean applyFactor) throws ConversionException {
        StringTokenizer tokens = new StringTokenizer(value, ",");
        Rectangle rect = new Rectangle();
        rect.x = (int) (applyFactor && factor != null ? Integer.parseInt(tokens.nextToken()) / factor : Integer.parseInt(tokens.nextToken()));
        rect.y = (int) (applyFactor && factor != null ? Integer.parseInt(tokens.nextToken()) / factor : Integer.parseInt(tokens.nextToken()));
        rect.width = (int) (applyFactor && factor != null ? Integer.parseInt(tokens.nextToken()) / factor : Integer.parseInt(tokens.nextToken()));
        rect.height = (int) (applyFactor && factor != null ? Integer.parseInt(tokens.nextToken()) / factor : Integer.parseInt(tokens.nextToken()));

        Parameter param = new Parameter();
        param.setParameterClass(Rectangle.class);
        param.setParameterInstance(rect);

        return param;
    }

}
