package charity.charity.domain;

import charity.charity.data.MemberRepository;
import charity.charity.models.Member;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member findById(int memberId) {
        return repository.findById(memberId);
    }

    public Result<Member> add(Member member) {
        Result<Member> result = validate(member);

        if (!result.isSuccess()) {
            return result;
        }

        if (member.getMemberId() != 0) {
            result.addMessage("memberId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        member = repository.add(member);
        result.setPayload(member);
        return result;
    }

    public Result<Member> update(Member member) {
        Result<Member> result = validate(member);
        if (!result.isSuccess()) {
            return result;
        }

        if (member.getMemberId() <= 0) {
            result.addMessage("memberId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(member)) {
            String msg = String.format("memberId: %s, not found", member.getMemberId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int memberId) {
        return repository.deleteById(memberId);
    }

    private Result<Member> validate(Member member) {
        Result<Member> result = new Result<>();
        if (member == null) {
            result.addMessage("member cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(member.getFirstName())) {
            result.addMessage("firstName is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(member.getLastName())) {
            result.addMessage("lastName is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(member.getEmail())) {
            result.addMessage("email is required", ResultType.INVALID);
        }

        return result;
    }
}
