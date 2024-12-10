package by.salex.spring.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import by.salex.spring.data.model.Account;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface AccountPessimisticRepository extends JpaRepository<Account, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "10000") })
    public Optional<Account> findById(Long id);
}
