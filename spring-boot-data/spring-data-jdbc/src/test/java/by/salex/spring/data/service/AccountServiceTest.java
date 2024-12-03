package by.salex.spring.data.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceTest extends BaseServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void updateAmountOptimisticLockTest() {
    }
}
