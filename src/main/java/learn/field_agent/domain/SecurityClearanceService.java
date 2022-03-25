package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {

    private final SecurityClearanceRepository repository;


    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
    }

    public SecurityClearance findById(int securityClearanceId) {
        return repository.findById(securityClearanceId);
    }

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        securityClearance = repository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }

    private Result<SecurityClearance> validate (SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Security Clearance Name is Required", ResultType.INVALID);
        }

        return result;
    }

    public Result<SecurityClearance> update (SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() <= 0) {
            result.addMessage("Security Clearance ID must be set for Update operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(securityClearance)) {
            String message = String.format("Security Clearance ID #%s Not Found",
                    securityClearance.getSecurityClearanceId());
            result.addMessage(message, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById (int securityClearanceId) {
        return repository.deleteById(securityClearanceId);
    }


}
