package by.salex.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.salex.spring.data.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
