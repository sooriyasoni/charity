package charity.charity.data;

import charity.charity.models.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository {
    List<Member> findAll();

    @Transactional
    Member findById(int memberId);

    Member add(Member member);

    boolean update(Member member);

    @Transactional
    boolean deleteById(int memberId);
}
