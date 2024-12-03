package by.salex.spring.data.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.salex.spring.data.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Transactional
    @Modifying
    @Query("update accounts set amount = :amount where id = :id and version = :version")
    boolean updateAmountWhereVersionIsCorrect(@Param("id") Long id, @Param("amount") Double amount,
            @Param("version") Long version);
}
