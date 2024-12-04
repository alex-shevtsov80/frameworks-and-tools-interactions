package by.salex.kafka.common;

public interface KafkaProperties {
    public String getProperty(String name);

    public <T> T getPropertyAs(String key);
}
