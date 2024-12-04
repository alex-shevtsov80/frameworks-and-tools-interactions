package by.salex.kafka.common;

import java.util.Properties;

public class SimpleKafkaProperties implements KafkaProperties {
    private final Properties properties;

    public SimpleKafkaProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPropertyAs(String key) {
        return (T) properties.get(key);
    }
}
