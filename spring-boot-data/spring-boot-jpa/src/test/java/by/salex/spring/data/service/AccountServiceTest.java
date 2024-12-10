package by.salex.spring.data.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import by.salex.spring.data.SpringDataJpaApp;

@SpringBootTest(classes = { SpringDataJpaApp.class })
@TestPropertySource("classpath:application-test.properties")
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void transferTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            try {
                accountService.transfer(1L, 2L, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                accountService.transfer(1L, 2L, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                accountService.transfer(1L, 2L, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    @Test
    public void transferPessimisticTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            try {
                accountService.transferPessimistic(1L, 2L, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                accountService.transferPessimistic(1L, 2L, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                accountService.transferPessimistic(1L, 2L, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }
}
