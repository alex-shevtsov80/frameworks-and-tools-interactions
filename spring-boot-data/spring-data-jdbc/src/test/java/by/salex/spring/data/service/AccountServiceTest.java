package by.salex.spring.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.salex.spring.data.model.Account;

public class AccountServiceTest extends BaseServiceTest {
    private static final int THREADS_COUNT = 10;

    @Autowired
    private AccountService accountService;

    @Test
    public void getByIdTest() {
        Account account = accountService.getById(1L);
        assertNotNull(account);
    }

    @Test
    public void getByIdNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> accountService.getById(1000L));
    }

    @Test
    public void updateAmountTest() {
        Account account = accountService.getById(1L);
        Double expectedAmount = account.getAmount() + 1;
        Long expectedVersion = account.getVersion() + 1;
        account = accountService.updateAmount(account.getId(), expectedAmount);
        assertEquals(expectedVersion, account.getVersion());
    }
}
