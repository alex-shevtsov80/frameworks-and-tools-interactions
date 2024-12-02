package by.salex.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.salex.spring.data.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
}
