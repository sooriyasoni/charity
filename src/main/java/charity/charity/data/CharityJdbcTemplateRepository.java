package charity.charity.data;

import charity.charity.data.mapper.CharityMapper;
import charity.charity.models.Charity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CharityJdbcTemplateRepository implements CharityRepository {
    private final JdbcTemplate jdbcTemplate;

    public CharityJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Charity> findAll(){
        final String sql = "Select * from charity;";
        return jdbcTemplate.query(sql, new CharityMapper());
    }

    @Override
    public List<Charity> findCharitiesByMember(int memberId) {
        final String sql = "SELECT candidate.title, " +
                "charity.charity_type_id, "+
                "charity.member_id, "+
                "candidate.name, " +
                "members.first_name, " +
                "members.last_name, " +
                "members.occupation, " +
                "members.phone, " +
                "members.address, " +
                "members.isMemberActive, " +
                "charity.charity_id, " +
                "candidate.candidate_id, " +
                "candidate.dob, "+
                "candidate.isCandidateActive, "+
                "payment.payment_id, "+
                "payment.status, "+
                "payment.type, "+
                "charity.donated_price, " +
                "charity.date, " +
                "charity_type.charity_type_id, "+
                "charity_type.type, "+
                "charity_type.description "+
                "from charity charity " +
                "inner join candidate candidate on candidate.candidate_id = charity.candidate_id " +
                "inner join members members on members.member_id = charity.member_id " +
                "inner join payment payment on payment.payment_id = charity.payment_id " +
                "inner join charity_type charity_type on charity_type.charity_type_id = charity.charity_type_id "+
                "WHERE charity.candidate_id=?;";

        return jdbcTemplate.query(sql, new CharityMapper(),memberId);
    }

    @Override
    public List<Charity> findCharitiesByCandidates(int candiateId) {

        final String sql = "SELECT candidate.title, " +
                "charity.charity_type_id, "+
                "charity.member_id, "+
                "candidate.name, " +
                "members.first_name, " +
                "members.last_name, " +
                "members.occupation, " +
                "members.phone, " +
                "members.address, " +
                "members.isMemberActive, " +
                "charity.charity_id, " +
                "candidate.candidate_id, " +
                "candidate.dob, "+
                "candidate.isCandidateActive, "+
                "payment.payment_id, "+
                "payment.status, "+
                "payment.type, "+
                "charity.donated_price, " +
                "charity.date, " +
                "charity_type.charity_type_id, "+
                "charity_type.type, "+
                "charity_type.description "+
                "from charity charity " +
                "inner join candidate candidate on candidate.candidate_id = charity.candidate_id " +
                "inner join members members on members.member_id = charity.member_id " +
                "inner join payment payment on payment.payment_id = charity.payment_id " +
                "inner join charity_type charity_type on charity_type.charity_type_id = charity.charity_type_id "+
                "WHERE charity.candidate_id=?;";
        return jdbcTemplate.query(sql, new CharityMapper(), candiateId);
    }



    @Override
    public Charity add(Charity charity) {

        final String sql = "insert into charity ( candidate_id, member_id, payment_id, charity_type_id, donated_price, `date`) "
                + " values (?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, charity.getCandidate().getCandidateId());
            ps.setInt(2, charity.getMember().getMemberId());
            ps.setInt(3, charity.getPayment().getPaymentId());
            ps.setInt(4, charity.getCharityType().getCharityTypeId());
            ps.setBigDecimal(5, charity.getDonatedAmount());
            ps.setDate(6, charity.getDate() == null ? null : Date.valueOf(charity.getDate()));
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        charity.setCharityId(keyHolder.getKey().intValue());
        return charity;
    }

}
