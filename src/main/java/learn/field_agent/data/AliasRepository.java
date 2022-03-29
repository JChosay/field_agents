package learn.field_agent.data;

import learn.field_agent.models.Alias;

import java.util.List;

public interface AliasRepository {
    Alias add(Alias alias);

    boolean update(Alias alias);

    List<Alias> findAll();

    boolean deleteById(int aliasId);
}
