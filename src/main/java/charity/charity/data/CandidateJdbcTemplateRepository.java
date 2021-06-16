package charity.charity.data;

import charity.charity.data.mapper.CandidateMapper;
import charity.charity.models.Candidate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CandidateJdbcTemplateRepository implements CandidateRepository {
    private final JdbcTemplate jdbcTemplate;

    public CandidateJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Candidate> findAll() {
        final String sql = "select candidate_id, name, title, dob, isCandidateActive "
                + "from candidate where isCandidateActive=1 limit 1000;";
        return jdbcTemplate.query(sql, new CandidateMapper());
    }

    @Override
    @Transactional
    public Candidate findById(int candidateId) {

        final String sql = "select candidate_id, name, title, dob , isCandidateActive "
                + "from candidate "
                + "where candidate_id = ?;";

        Candidate candidate = jdbcTemplate.query(sql, new CandidateMapper(), candidateId).stream()
                .findFirst().orElse(null);


        return candidate;
    }

    @Override
    public Candidate add(Candidate candidate) {

        final String sql = "insert into candidate (name, title, dob) "
                + " values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getTitle());
            ps.setDate(3, candidate.getDob() == null ? null : Date.valueOf(candidate.getDob()));
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        candidate.setCandidateId(keyHolder.getKey().intValue());
        return candidate;
    }

    @Override
    public boolean update(Candidate candidate) {

        final String sql = "update candidate set "
                + "name = ?, "
                + "title = ?, "
                + "dob = ? "
                + "where candidate_id = ?;";

        boolean updated = jdbcTemplate.update(sql,
                candidate.getName(),
                candidate.getTitle(),
                candidate.getDob(),
                candidate.getCandidateId()) > 0;
        return updated;
    }

    @Override
    @Transactional
    public boolean deleteById(int candidateId) {
        int result=jdbcTemplate.update("update candidate set isCandidateActive=0 where candidate_id = ?", candidateId);
        boolean deleted =  result> 0;
        return deleted;
    }

}
