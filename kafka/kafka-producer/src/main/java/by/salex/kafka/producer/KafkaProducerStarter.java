package by.salex.kafka.producer;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.salex.kafka.common.PropertiesProvider;

public class KafkaProducerStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerStarter.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting producer...");
        // load properties
        Properties properties = PropertiesProvider.fromResource();
        String topic = properties.getProperty("topic");
        // kafka producer
        try (KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties)) {

            // send hello message
            sendSingleSimpleMessage(producer, topic, "java producer online!");

            // callback test message
            sendMessage(producer, topic, null, "[%s] callback.".formatted(System.currentTimeMillis()), true,
                    (metadata, exception) -> {
                        if (exception == null) {
                            StringBuilder callbackResult = new StringBuilder("\nMetadata received: \n");
                            callbackResult.append("Topic: ").append(metadata.topic()).append("\n");
                            callbackResult.append("Partition: ").append(metadata.partition()).append("\n");
                            callbackResult.append("Offset: ").append(metadata.offset()).append("\n");
                            callbackResult.append("Timestamp: ").append(metadata.timestamp()).append("\n");
                            LOGGER.info(callbackResult.toString());
                        } else {
                            LOGGER.error(exception.getMessage());
                        }
                    });

            // batch test
            String messagesCount = properties.getProperty("test.batch.messages.count");
            if (messagesCount != null) {
                int mc = Integer.valueOf(messagesCount);
                for (int i = 0; i < mc; i++) {
                    String key = "key_" + i % 10;
                    String message = null;
                    if (mc - 1 == i) {
                        message = "stop";
                    } else {
                        message = "[%s - %s] message %s.".formatted(System.currentTimeMillis(), key, i);
                    }
                    sendMessage(producer, topic, key, message, true, (metadata, exception) -> {
                        if (exception == null) {
                            StringBuilder callbackResult = new StringBuilder("\nMetadata received: \n");
                            callbackResult.append("Key: ").append(key).append(" ");
                            callbackResult.append("Partition: ").append(metadata.partition()).append("\n");
                            LOGGER.info(callbackResult.toString());
                        } else {
                            LOGGER.error(exception.getMessage());
                        }
                    });
                }
                producer.flush();
            }
        }

        LOGGER.info("Bye bye!");
    }

    private static void sendMessage(KafkaProducer<String, String> producer, String topic, String key, String message,
            boolean useFlash, Callback callback) {
        ProducerRecord<String, String> record = null;
        if (key != null) {
            record = new ProducerRecord<String, String>(topic, key, message);
        } else {
            record = new ProducerRecord<String, String>(topic, message);
        }
        producer.send(record, callback);
        if (useFlash) {
            producer.flush();
        }
    }

    private static void sendSingleSimpleMessage(KafkaProducer<String, String> producer, String topic, String message) {
        sendMessage(producer, topic, null, message, true, null);
    }
}
