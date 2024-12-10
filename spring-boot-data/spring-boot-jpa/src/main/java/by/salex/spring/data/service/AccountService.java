package by.salex.spring.data.service;

import by.salex.spring.data.model.Account;

public interface AccountService {
    void transfer(Long fromAccountId, Long toAccountId, double amount);

    void transfer(Account fromAccount, Account toAccount, double amount);

    void transferPessimistic(Long fromAccountId, Long toAccountId, double amount);

}
