package by.salex.kafka.common;

import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider {
    private static final String DEFAULT_RESOURCE_NAME = "application.properties";

    private PropertiesProvider() {
    }

    public static Properties fromResource() throws IOException {
        return fromResource(DEFAULT_RESOURCE_NAME);
    }

    public static Properties fromResource(String resourceName) throws IOException {
        Properties properties = new Properties();
        ClassLoader classLoader = PropertiesProvider.class.getClassLoader();
        properties.load(classLoader.getResourceAsStream(resourceName));
        return properties;
    }
}
