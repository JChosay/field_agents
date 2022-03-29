package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AliasJdbcTemplateRepositoryTest {

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAliases () {
        Alias alias = new Alias();
        alias.setName("Test Alias");
        alias.setPersona("Test Persona");
        alias.setAgentId(2);
        repository.add(alias);
        List<Alias> aliases = repository.findAll();
        assertNotNull(aliases);
        assertTrue(aliases.size() > 0);
    }

    @Test
    void shouldAddAlias () {
        Alias alias = new Alias();
        alias.setName("Test Alias 2");
        alias.setPersona("Test Persona");
        alias.setAgentId(1);
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(1, actual.getAliasId());
    }

    @Test
    void shouldUpdateAlias () {
        Alias alias = new Alias();
        alias.setName("Test Alias 3");
        alias.setPersona("Test Persona");
        alias.setAgentId(2);
        repository.add(alias);

        Alias update = new Alias();
        update.setAliasId(2);
        update.setName("Test Alias 4");
        update.setPersona("Test Persona 4");
        assertTrue(repository.update(update));
    }

    @Test
    void shouldDeleteAlias () {
        Alias alias = new Alias();
        alias.setName("Test Alias 5");
        alias.setPersona("Test Persona");
        alias.setAgentId(3);
        Alias actual = repository.add(alias);

        assertTrue(repository.deleteById(3));
    }
}
