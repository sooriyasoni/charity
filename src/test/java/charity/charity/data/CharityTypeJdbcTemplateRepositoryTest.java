package charity.charity.data;

import charity.charity.models.CharityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CharityTypeJdbcTemplateRepositoryTest {

    @Autowired
    CharityTypeJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<CharityType> charityTypes = repository.findAll();
        assertNotNull(charityTypes);

        assertTrue(charityTypes.size() >= 3);
    }

    @Test
    void shouldFindSoorya() {
        CharityType birthday = repository.findById(1);
        assertEquals(1, birthday.getCharityTypeId());
        assertEquals("Birthday", birthday.getType());

    }
}