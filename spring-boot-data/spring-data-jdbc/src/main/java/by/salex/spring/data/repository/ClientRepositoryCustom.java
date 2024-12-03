package by.salex.spring.data.repository;

import java.util.List;

import by.salex.spring.data.model.ClientWithFullAmount;

public interface ClientRepositoryCustom {
    List<ClientWithFullAmount> findAllClientsWhereFullAmountMoreThan(double anount);
}
