package charity.charity.domain;

import charity.charity.data.CandidateRepository;
import charity.charity.models.Candidate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidateService {
    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public List<Candidate> findAll() {
        return repository.findAll();
    }

    public Candidate findById(int candidateId) {
        return repository.findById(candidateId);
    }

    public Result<Candidate> add(Candidate candidate) {
        Result<Candidate> result = validate(candidate);

        if (!result.isSuccess()) {
            return result;
        }

        if (candidate.getCandidateId() != 0) {
            result.addMessage("CandidateId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        candidate = repository.add(candidate);
        result.setPayload(candidate);
        return result;
    }

    public Result<Candidate> update(Candidate candidate) {
        Result<Candidate> result = validate(candidate);
        if (!result.isSuccess()) {
            return result;
        }

        if (candidate.getCandidateId() <= 0) {
            result.addMessage("CandidateId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(candidate)) {
            String msg = String.format("CandidateId: %s, not found", candidate.getCandidateId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int candidateId) {
        return repository.deleteById(candidateId);
    }

    private Result<Candidate> validate(Candidate candidate) {
        Result<Candidate> result = new Result<>();
        if (candidate == null) {
            result.addMessage("Candidate cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(candidate.getName())) {
            result.addMessage("name is required", ResultType.INVALID);
        }

        if (candidate.getDob() != null && candidate.getDob().isAfter(LocalDate.now().minusYears(12))) {
            result.addMessage("dob is required", ResultType.INVALID);
        }

        return result;

    }
}
