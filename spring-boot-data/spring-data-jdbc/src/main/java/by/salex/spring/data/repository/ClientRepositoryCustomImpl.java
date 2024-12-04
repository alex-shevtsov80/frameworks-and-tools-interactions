package by.salex.spring.data.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import by.salex.spring.data.model.ClientWithFullAmount;

@Transactional(readOnly = true)
public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {
    @Autowired
    private JdbcTemplate template;

    @Override
    public List<ClientWithFullAmount> findAllClientsWhereFullAmountMoreThan(double amount) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select accounts.client_id, clients.first_name, sum(accounts.amount) as amount \n");
        sqlBuilder.append("from accounts \n");
        sqlBuilder.append("join clients on (clients.id = accounts.client_id and accounts.active=true) \n");
        sqlBuilder.append("group by (accounts.client_id, clients.first_name) \n");
        sqlBuilder.append("having sum(accounts.amount) >= ").append(amount).append(";");
        return template.query(sqlBuilder.toString(), new ClientWithFullAmountRowMapper());
    }

    private static class ClientWithFullAmountRowMapper implements RowMapper<ClientWithFullAmount> {
        @Override
        public ClientWithFullAmount mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClientWithFullAmount cwfa = new ClientWithFullAmount();
            cwfa.setId(rs.getLong("client_id"));
            cwfa.setName(rs.getString("first_name"));
            cwfa.setAmount(rs.getDouble("amount"));
            return cwfa;
        }
    }
}
