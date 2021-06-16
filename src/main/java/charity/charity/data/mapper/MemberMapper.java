package charity.charity.data.mapper;

import charity.charity.models.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        Member member = new Member();
        member.setMemberId(resultSet.getInt("member_id"));
        member.setFirstName(resultSet.getString("first_name"));
        member.setLastName(resultSet.getString("last_name"));
        member.setOccupation(resultSet.getString("occupation"));
        member.setPhone(resultSet.getString("phone"));
        member.setAddress(resultSet.getString("address"));
        member.setIsMemberActive(resultSet.getInt("isMemberActive"));
        return member;
    }
}
