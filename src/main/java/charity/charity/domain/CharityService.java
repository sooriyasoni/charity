package charity.charity.domain;

import charity.charity.data.CandidateRepository;
import charity.charity.data.CharityRepository;
import charity.charity.models.Candidate;
import charity.charity.models.Charity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CharityService {

    private final CharityRepository repository;

    public CharityService(CharityRepository repository) {
        this.repository = repository;
    }

    public List<Charity> findAll() {
        return repository.findAll();
    }

    public List<Charity> findMembersCharity(int memberId) {
        return repository.findCharitiesByMember(memberId);
    }

    public List<Charity> findCandidatesCharity(int candidateId) {
        return repository.findCharitiesByCandidates(candidateId);
    }

    public Result<Charity> add(Charity charity) {
        Result<Charity> result = validate(charity);

        if (!result.isSuccess()) {
            return result;
        }

        if (charity.getCharityId() != 0) {
            result.addMessage("charityId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        charity = repository.add(charity);
        result.setPayload(charity);
        return result;
    }

    private Result<Charity> validate(Charity charity) {
        Result<Charity> result = new Result<>();
        if (charity == null) {
            result.addMessage("charity cannot be null", ResultType.INVALID);
            return result;
        }

        if (charity.getDonatedAmount().doubleValue() <= 0.0) {
            result.addMessage("Please enter a valid amount ", ResultType.INVALID);
        }

        return result;
    }
    }