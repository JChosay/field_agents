package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.springframework.transaction.annotation.Transactional;

public interface SecurityClearanceRepository {

    @Transactional
    SecurityClearance findById(int securityClearanceId);

}
