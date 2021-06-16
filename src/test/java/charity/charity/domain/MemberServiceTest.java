package charity.charity.domain;
import charity.charity.data.MemberRepository;
import charity.charity.models.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MemberServiceTest {

    @Autowired
    MemberService service;

    @MockBean
    MemberRepository repository;

    @Test
    void shouldFindHazel() {
        // pass-through test, probably not useful
        Member expected = makeMember();
        when(repository.findById(1)).thenReturn(expected);
        Member actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Member member = makeMember();
        Result<Member> result = service.add(member);
        assertEquals(ResultType.INVALID, result.getType());

        member.setMemberId(0);
        member.setFirstName(null);
        result = service.add(member);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenValid() {
        Member expected = makeMember();
        Member arg = makeMember();
        arg.setMemberId(0);

        when(repository.add(arg)).thenReturn(expected);
        Result<Member> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    Member makeMember() {
        //('Hazel','C','Sauven','1954-09-16',76),
        Member member = new Member();
        member.setMemberId(1);
        member.setFirstName("Hazel");
        member.setLastName("Sauven");
        member.setAddress("Ha24yw");
        member.setOccupation("developer");
        member.setPhone("Test");
        member.setEmail("test@test.com");
        return member;
    }
}