package org.pilirion.nakaza.api;

import org.apache.wicket.resource.Properties;
import org.pilirion.nakaza.Nakaza;

/**
 * Utilize loading resources for class.
 */
public class ResourceLoader {
    public static Properties getProperties(Class<?> clazz, String path){
        return Nakaza.get().getResourceSettings().getPropertiesFactory().load(clazz, path);
    }
}
