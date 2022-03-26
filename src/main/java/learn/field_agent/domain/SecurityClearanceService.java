package learn.field_agent.domain;

import learn.field_agent.data.AgencyAgentRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SecurityClearanceService {

    private final SecurityClearanceRepository repository;
    private final AgencyAgentRepository agencyAgentRepository;

    public SecurityClearanceService(SecurityClearanceRepository repository, AgencyAgentRepository agencyAgentRepository) {
        this.repository = repository;
        this.agencyAgentRepository = agencyAgentRepository;
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
//        Result<SecurityClearance> result = validateTypeDelete(securityClearanceId);

        return repository.deleteById(securityClearanceId);
    }

    private Result<SecurityClearance> validate (SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Security Clearance Name is Required", ResultType.INVALID);
        }

        List<SecurityClearance> all = repository.findAll();
        for (SecurityClearance s : all){
            if (Objects.equals(s.getName(), securityClearance.getName())){
                result.addMessage("You May Not Enter a Duplicate Security Clearance Name.", ResultType.INVALID);
                return result;
            }
        }

        return result;
    }

//    private Result<SecurityClearance> validateTypeDelete (Integer securityClearanceId) {
//        Result<SecurityClearance> result = new Result<>();
//        int count = 0;
//        List<AgencyAgent> all = agencyAgentRepository.findAll();
//        for (AgencyAgent a : all) {
//            if (a.getSecurityClearance().getSecurityClearanceId()==securityClearanceId){
//                count ++;
//            }
//        }
//        if (count != 0){
//            result.addMessage("This security clearance is currently in use and may not be deleted.", ResultType.INVALID);
//            return result;
//        }
//        return result;
//    }


}
