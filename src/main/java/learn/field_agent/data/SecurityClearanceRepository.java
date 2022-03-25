package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SecurityClearanceRepository {

    @Transactional
    SecurityClearance findById(int securityClearanceId);

    List<SecurityClearance> findAll();

    SecurityClearance add(SecurityClearance securityClearance);

    boolean update(SecurityClearance securityClearance);
}
