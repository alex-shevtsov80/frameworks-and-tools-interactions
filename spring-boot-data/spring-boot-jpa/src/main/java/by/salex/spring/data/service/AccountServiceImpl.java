package by.salex.spring.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import by.salex.spring.data.model.Account;
import by.salex.spring.data.repository.AccountPessimisticRepository;
import by.salex.spring.data.repository.AccountRepository;
import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    private static Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountPessimisticRepository accountPessimisticRepository;

    @Lazy
    @Autowired
    private AccountService accountService;

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException());
        if (fromAccount.getAmount() < amount) {
            throw new IllegalArgumentException();
        }
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new IllegalArgumentException());
        accountService.transfer(fromAccount, toAccount, amount);
    }

    @Transactional
    @Override
    public void transfer(Account fromAccount, Account toAccount, double amount) {
        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);
        fromAccount = accountRepository.save(fromAccount);
        toAccount = accountRepository.save(toAccount);
        LOGGER.info("fromAccount: {}, toAccount: {}", fromAccount.getAmount(), toAccount.getAmount());
    }

    @Transactional
    @Override
    public void transferPessimistic(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = accountPessimisticRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException());
        if (fromAccount.getAmount() < amount) {
            throw new IllegalArgumentException();
        }
        Account toAccount = accountPessimisticRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException());
        
        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);
        fromAccount = accountPessimisticRepository.save(fromAccount);
        toAccount = accountPessimisticRepository.save(toAccount);
        LOGGER.info("fromAccount: {}, toAccount: {}", fromAccount.getAmount(), toAccount.getAmount());
    }
}
