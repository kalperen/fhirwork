/*
 * FHIRWork (c) 2018 - Blair Butterworth, Abdul-Qadir Ali, Xialong Chen,
 * Chenghui Fan, Alperen Karaoglu, Jiaming Zhou
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package org.ucl.fhirwork.common.resources;

import java.io.File;
import java.net.URL;

/**
 * Instances of this class provide convenience functions for using files
 * located in the resources directory.
 *
 * @author Blair Butterworth
 */
public class Resources
{
    public static File getResource(String resource)
    {
        URL templateUrl = Thread.currentThread().getContextClassLoader().getResource(resource);
        return new File(templateUrl.getPath());
    }
}