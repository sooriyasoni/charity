package charity.charity.data;

import charity.charity.models.Charity;

import java.util.List;

public interface CharityRepository {
    List<Charity> findAll();

    List<Charity> findCharitiesByMember(int memberId);

    List<Charity> findCharitiesByCandidates(int candiateId);

    Charity add(Charity charity);
}
