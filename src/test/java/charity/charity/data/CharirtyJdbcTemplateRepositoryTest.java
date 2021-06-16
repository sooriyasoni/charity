package charity.charity.data;

import charity.charity.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CharirtyJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 3;

    @Autowired
    CharityRepository repository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll(){
        List<Charity> charities = repository.findCharitiesByCandidates(1);
        assertNotNull(charities);

        assertTrue(charities.size() >= 1);
    }

    @Test
    void shouldFindCharityByCandidate(){
        List<Charity> candidates = repository.findCharitiesByCandidates(1);
        assertNotNull(candidates);

        assertTrue(candidates.size() >= 1);
    }


    @Test
    void shouldFindCharityByMember(){
        List<Charity> candidates = repository.findCharitiesByMember(1);
        assertNotNull(candidates);

        assertTrue(candidates.size() >= 1);
    }


    @Test
    void shouldAddCharity(){
        //payemnt should me made if the record needs to be added in charity
            Payment payment = makeCharity().getPayment();
            payment.setPaymentId(0);
            paymentRepository.add(payment);

            Charity charity = makeCharity();
            charity.setCharityId(0);
            Charity charityResult = repository.add(charity);
            assertNotNull (charityResult);
            assertEquals(NEXT_ID+1, charityResult.getCharityId());
    }

    Charity makeCharity() {
        Payment payment = new Payment();
        payment.setPaymentId(4);
        payment.setStatus("Success");
        payment.setType("DebitCard");

        Candidate candidate = new Candidate();
        candidate.setCandidateId(1);
        candidate.setName("Test");
        candidate.setTitle("Mr");
        candidate.setDob(LocalDate.of(2008, 2, 2));

        Member member = new Member();
        member.setMemberId(1);
        member.setFirstName("Test");
        member.setLastName("Last Name");
        member.setAddress("HA1 4YW");
        member.setOccupation("business");
        member.setPhone("98989809");

        CharityType charityType = new CharityType();
        charityType.setCharityTypeId(1);
        charityType.setType("Birthday");
        charityType.setDesciprtion("");

        Charity charity = new Charity();
        charity.setDonatedAmount(new BigDecimal(100.55));
        charity.setDate(LocalDate.of(2010, 6, 19));
        charity.setMember(member);
        charity.setCandidate(candidate);
        charity.setPayment(payment);
        charity.setCharityType(charityType);

        return charity;

    }
}
