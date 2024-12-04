package by.salex.spring.data.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.salex.spring.data.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Modifying
    @Query("update accounts set amount = :amount, version = :newVersion where id = :id and version = :version")
    boolean updateAmount(@Param("id") Long id, @Param("amount") Double amount, @Param("version") Long version,
            @Param("newVersion") Long newVersion);
}
