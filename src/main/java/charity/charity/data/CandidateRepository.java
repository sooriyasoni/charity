package charity.charity.data;

import charity.charity.models.Candidate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CandidateRepository {
    List<Candidate> findAll();

    @Transactional
    Candidate findById(int candidateId);

    Candidate add(Candidate candidate);

    boolean update(Candidate candidate);

    @Transactional
    boolean deleteById(int candidateId);
}
