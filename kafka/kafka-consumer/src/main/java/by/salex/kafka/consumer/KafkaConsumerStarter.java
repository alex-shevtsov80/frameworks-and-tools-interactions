package by.salex.kafka.consumer;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.salex.kafka.common.PropertiesProvider;

public class KafkaConsumerStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerStarter.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting consumer...");

        Properties properties = PropertiesProvider.fromResource();
        String topic = properties.getProperty("topic");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties)) {
            consumer.subscribe(List.of(topic));
            while (true) {
                LOGGER.info("Polling...");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    LOGGER.info("Partition=%s, Offset=%s, Key=%s, Value=%s".formatted(record.partition(),
                            record.offset(), record.key(), record.value()));
                }
            }
        }
    }
}
