package learn.field_agent.data.mappers;

import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AliasMapper implements RowMapper<Alias> {

    @Override
    public Alias mapRow(ResultSet resultSet, int i) throws SQLException {
        Alias alias = new Alias();
        alias.setAliasId(resultSet.getInt("alias_Id"));
        alias.setName(resultSet.getString("name"));
        alias.setPersona(resultSet.getString("persona"));

        AgentMapper agentMapper = new AgentMapper();
        alias.setAgent(agentMapper.mapRow(resultSet, i));

        return alias;
    }
}