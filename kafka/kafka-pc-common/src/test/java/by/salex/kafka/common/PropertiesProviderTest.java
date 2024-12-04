package by.salex.kafka.common;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class PropertiesProviderTest {
    @Test
    public void loadAsResourceTest() throws IOException {
        Properties properties = PropertiesProvider.fromResource("test.properties");
        assertNotNull(properties);
        assertNotNull(properties.getProperty("test"));
    }
}
