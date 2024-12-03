package by.salex.spring.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.salex.spring.data.model.Account;
import by.salex.spring.data.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User(%s) not found", id)));
    }

    @Override
    public Account updateAmount(Long id, Double amount) {
        Account account = getById(id);
        if (!accountRepository.updateAmount(account.getId(), amount, account.getVersion(), account.getVersion() + 1)) {
            throw new RuntimeException("Optimistic lock exception");
        }
        return getById(id);
    }
}
