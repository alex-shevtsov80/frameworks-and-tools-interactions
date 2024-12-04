package by.salex.spring.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.salex.spring.data.model.Account;

public class AccountRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findAllTest() {
        var accounts = accountRepository.findAll();
        assertNotNull(accounts);
    }

    @Test
    public void updateAmountWhereVersionIsCorrectTest() {
        Account account = accountRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException());
        Double expectedAmount = account.getAmount() + 1;
        Long expectedVersion = account.getVersion() + 1;
        account.setAmount(expectedAmount);
        assertTrue(accountRepository.updateAmount(account.getId(), account.getAmount(), account.getVersion(),
                account.getVersion() + 1));
        account = accountRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException());
        assertEquals(expectedAmount, account.getAmount());
        assertEquals(expectedVersion, account.getVersion());
    }
}
