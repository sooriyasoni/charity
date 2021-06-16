package charity.charity.data;

import charity.charity.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MemberJdbcTemplateRepositoryTest {

        @Autowired
        MemberJdbcTemplateRepository repository;

        @Autowired
        KnownGoodState knownGoodState;

        @BeforeEach
        void setup() {
            knownGoodState.set();
        }

        @Test
        void shouldFindAll() {
            List<Member> members = repository.findAll();
            assertNotNull(members);

            assertTrue(members.size() >= 3);
        }

        @Test
        void shouldFindSoorya() {
            Member soorya = repository.findById(1);
            assertEquals(1, soorya.getMemberId());
            assertEquals("Soorya", soorya.getFirstName());
        }

        @Test
        void shouldAdd() {
            // all fields
            Member member = makeMember();
            Member actual = repository.add(member);
            assertNotNull(actual);
            assertEquals(member.getFirstName(), actual.getFirstName());
        }

        @Test
        void shouldUpdate() {
            Member member = makeMember();
            member.setMemberId(3);
            assertTrue(repository.update(member));
            member.setMemberId(13);
            assertFalse(repository.update(member));
        }


        @Test
        void shouldDelete() {
            assertTrue(repository.deleteById(4));
            assertFalse(repository.deleteById(10));
        }

        private Member makeMember() {
            Member member = new Member();
            member.setFirstName("Test");
            member.setLastName("Last Name");
            member.setAddress("HA1 4YW");
            member.setOccupation("business");
            return member;
        }
    }