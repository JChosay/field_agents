package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AliasService {

    private final AliasRepository repository;


    public AliasService(AliasRepository repository) {
        this.repository = repository;
    }

    public List<Alias> findAll() {
        return repository.findAll();
    }
}
