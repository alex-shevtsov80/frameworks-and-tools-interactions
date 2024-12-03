package by.salex.spring.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.salex.spring.data.model.ClientWithFullAmount;

public class ClientRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void findAllTest() {
        var clients = clientRepository.findAll();
        assertNotNull(clients);
    }

    @Test
    public void findAllClientsWhereFullAmountMoreThanTest() {
        List<ClientWithFullAmount> clients = clientRepository.findAllClientsWhereFullAmountMoreThan(2000);
        assertNotNull(clients);
        assertEquals(1, clients.size());
        assertEquals("User_2", clients.get(0).getName());
    }
}
