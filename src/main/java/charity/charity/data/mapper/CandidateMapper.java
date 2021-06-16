package charity.charity.data.mapper;

import charity.charity.models.Candidate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateMapper  implements RowMapper<Candidate> {
    @Override
    public Candidate mapRow(ResultSet resultSet, int i) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(resultSet.getInt("candidate_id"));
        candidate.setName(resultSet.getString("name"));
        candidate.setTitle(resultSet.getString("title"));
        if (resultSet.getDate("dob") != null) {
            candidate.setDob(resultSet.getDate("dob").toLocalDate());
        }
        candidate.setIsCandidateActive(resultSet.getInt("isCandidateActive"));
        return candidate;
    }
}