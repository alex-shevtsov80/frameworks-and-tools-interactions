package by.salex.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.salex.spring.data.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
