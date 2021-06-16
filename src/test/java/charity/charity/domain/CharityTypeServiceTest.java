package charity.charity.domain;

import charity.charity.data.CharityTypeRepository;
import charity.charity.data.MemberRepository;
import charity.charity.models.CharityType;
import charity.charity.models.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CharityTypeServiceTest {
    @Autowired
    CharityTypeService service;

    @MockBean
    CharityTypeRepository repository;

    @Test
    void shouldFindBirthday() {
        CharityType expected = makeCharityType();
        when(repository.findById(1)).thenReturn(expected);
        CharityType actual = service.findById(1);
        assertEquals(expected, actual);
    }

    CharityType makeCharityType() {
        CharityType charityType = new CharityType();
        charityType.setCharityTypeId(1);
        charityType.setType("Birthday");
        charityType.setDesciprtion("");
        return charityType;
    }

}
