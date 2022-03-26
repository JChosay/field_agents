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

    public Result<Alias> add (Alias alias) {
        Result<Alias> result = validate(alias);
        if (!result.isSuccess()) {
            return result;
        }

        alias = repository.add(alias);
        result.setPayload(alias);
        return result;
    }

    private Result<Alias> validate (Alias alias) {
        Result<Alias> result = new Result<>();

        if (Validations.isNullOrBlank(alias.getName())){
            result.addMessage("Alias 'name' field is required.", ResultType.INVALID);
        }

        List<Alias> all = repository.findAll();
        for (Alias a : all){
            if (a.getName().equalsIgnoreCase(alias.getName())){
                if (alias.getPersona()==null){
                    result.addMessage("For aliases with a duplicate name, a persona is required.", ResultType.INVALID);
                }
            }
        }

        return result;
    }
}
