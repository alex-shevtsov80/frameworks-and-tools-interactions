package by.salex.spring.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.salex.spring.data.model.Account;
import by.salex.spring.data.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public Account updateAmount(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setAmount(amount);
            if (accountRepository.updateAmountWhereVersionIsCorrect(account.getId(), amount, account.getVersion())) {
                account.setVersion(account.getVersion() + 1);
                account = accountRepository.save(account);
            }
        }
        return account;
    }
}
