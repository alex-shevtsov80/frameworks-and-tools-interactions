package by.salex.spring.data.service;

import by.salex.spring.data.model.Account;

public interface AccountService {
    Account updateAmount(Long id, Double amount);
}
