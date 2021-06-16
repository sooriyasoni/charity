package charity.charity.domain;

import charity.charity.data.CharityRepository;
import charity.charity.data.PaymentStatus;
import charity.charity.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CharityServiceTest {

    @Autowired
    CharityService service;

    @MockBean
    CharityRepository repository;

    @Test
    void shouldFindAll() {
        List<Charity> expected = Arrays.asList(makeCharity());
        when(repository.findAll()).thenReturn(expected);
        List<Charity> actual = service.findAll();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());

        //should assert false
        assertNotEquals(0, actual.size());
    }

    @Test
    void shouldFindByMember() {
        List<Charity> expected = Arrays.asList(makeCharity());
        when(repository.findCharitiesByMember(1)).thenReturn(expected);
        List<Charity> actual = service.findMembersCharity(1);
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());

        //should assert false
        assertNotEquals(0, actual.size());
    }

    @Test
    void shouldFindByCandidate() {
        List<Charity> expected = Arrays.asList(makeCharity());
        when(repository.findCharitiesByCandidates(1)).thenReturn(expected);
        List<Charity> actual = service.findCandidatesCharity(1);
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());

        //should assert false
        assertNotEquals(0, actual.size());
    }

    Charity makeCharity() {
        Payment payment = new Payment();
        payment.setPaymentId(1);
        payment.setStatus(PaymentStatus.PAID.toString());
        payment.setType("DebitCard");

        Candidate candidate = new Candidate();
        candidate.setCandidateId(1);
        candidate.setName("Test");
        candidate.setTitle("Mr");
        candidate.setDob(LocalDate.of(2008, 2, 2));
        candidate.setIsCandidateActive(1);

        Member member = new Member();
        member.setMemberId(1);
        member.setFirstName("Test");
        member.setLastName("Last Name");
        member.setAddress("HA1 4YW");
        member.setOccupation("business");
        member.setPhone("98989809");
        member.setIsMemberActive(1);

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
        charity.setCharityId(1);

        return charity;

    }

    @Test
    void shouldAdd() {
        Charity charity = makeCharity();
        Charity mockOut = makeCharity();
        charity.setCharityId(0);

        when(repository.add(charity)).thenReturn(mockOut);

        Result<Charity> actual = service.add(charity);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }
}