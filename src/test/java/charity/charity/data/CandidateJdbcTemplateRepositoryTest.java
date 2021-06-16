package charity.charity.data;
import charity.charity.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CandidateJdbcTemplateRepositoryTest {

    @Autowired
    CandidateJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shoudFindAll(){
        List<Candidate> candidates = repository.findAll();
        assertNotNull(candidates);

        assertTrue(candidates.size() >= 3);
    }

    @Test
    void shouldFindSoorya() {
        Candidate soorya = repository.findById(1);
        assertEquals(1, soorya.getCandidateId());
        assertEquals("Soorya", soorya.getName());
    }

    @Test
    void shouldAdd() {
        Candidate candidate = makeCandidate();
        candidate.setCandidateId(0);
        Candidate actual = repository.add(candidate);
        assertNotNull(actual);
        assertEquals(candidate.getName(), actual.getName());
    }


    @Test
    void shouldUpdate() {
        Candidate candidate = makeCandidate();
        candidate.setCandidateId(3);
        assertTrue(repository.update(candidate));
        candidate.setCandidateId(13);
        assertFalse(repository.update(candidate));
    }

    //ToDo : To Fix Later
    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(10));
    }

    Candidate makeCandidate() {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(3);
        candidate.setName("Testing");
        candidate.setTitle("Mr");
        candidate.setDob(LocalDate.of(2007,02,03));
        return candidate;

    }
}
