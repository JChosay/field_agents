package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void shouldFindById () {
        SecurityClearance expected = makeClearance();
        when(repository.findById(3)).thenReturn(expected);
        SecurityClearance actual = service.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAddClearance () {
        SecurityClearance securityClearance = new SecurityClearance(0,"Test CLearance");
        SecurityClearance mock = new SecurityClearance(3,"Test CLearance");

        when(repository.add(securityClearance)).thenReturn(mock);

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mock, actual.getPayload());

    }

    @Test
    void shouldUpdateClearance () {
        SecurityClearance securityClearance = new SecurityClearance(3, "Update");

        when(repository.update(securityClearance)).thenReturn(true);
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }



    SecurityClearance makeClearance () {
        SecurityClearance clearance = new SecurityClearance();
        clearance.setSecurityClearanceId(3);
        clearance.setName("Eyes-only");
        return clearance;
    }
}
