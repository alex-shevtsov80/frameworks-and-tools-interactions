package by.salex.spring.data.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findAllTest() {
        var accounts = accountRepository.findAll();
        assertNotNull(accounts);
    }
}
