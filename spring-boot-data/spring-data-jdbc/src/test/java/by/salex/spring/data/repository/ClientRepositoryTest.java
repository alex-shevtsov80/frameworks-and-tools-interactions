package by.salex.spring.data.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void findAllTest() {
        var clients = clientRepository.findAll();
        assertNotNull(clients);
    }
}
