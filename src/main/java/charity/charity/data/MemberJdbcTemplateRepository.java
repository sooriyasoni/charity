package charity.charity.data;

import charity.charity.data.mapper.MemberMapper;
import charity.charity.models.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MemberJdbcTemplateRepository implements MemberRepository {
private final JdbcTemplate jdbcTemplate;

    public MemberJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Member> findAll() {
        final String sql = "select member_id, first_name, last_name, occupation, phone ,email, address, isMemberActive "
                + "from members where isMemberActive=1 limit 1000;";
        return jdbcTemplate.query(sql, new MemberMapper());
    }

    @Override
    @Transactional
    public Member findById(int memberId) {

        final String sql = "select member_id, first_name, last_name, occupation, phone, email, address, isMemberActive "
                + "from members "
                + "where member_id = ?;";

        Member member = jdbcTemplate.query(sql, new MemberMapper(), memberId).stream()
                .findFirst().orElse(null);


        return member;
    }

    @Override
    public Member add(Member member) {

        final String sql = "insert into members (first_name, last_name, occupation, phone, email, address) "
                + " values (?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getFirstName());
            ps.setString(2, member.getLastName());
            ps.setString(3, member.getOccupation());
            ps.setString(4, member.getPhone());
            ps.setString(5, member.getEmail());
            ps.setString(6, member.getAddress());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        member.setMemberId(keyHolder.getKey().intValue());
        return member;
    }

    @Override
    public boolean update(Member member) {

        final String sql = "update members set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "occupation = ?, "
                + "phone = ?, "
                + "email = ? ,"
                + "address = ? "
                + "where member_id = ?;";

        return jdbcTemplate.update(sql,
                member.getFirstName(),
                member.getLastName(),
                member.getOccupation(),
                member.getPhone(),
                member.getEmail(),
                member.getAddress(),
                member.getMemberId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int memberId) {
        int result=jdbcTemplate.update("update members set isMemberActive=0 where member_id = ?", memberId);
        boolean deleted =  result> 0;
        return deleted;
    }
}
