package by.salex.kafka.producer;

import java.io.IOException;
import java.util.Properties;

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
            ProducerRecord<String, String> helloRecord = new ProducerRecord<String, String>(topic,
                    "java producer online!");
            producer.send(helloRecord);
            producer.flush();
        }
        LOGGER.info("Bye bye!");
    }
}
