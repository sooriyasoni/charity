package charity.charity.data.mapper;

import charity.charity.models.Charity;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CharityMapper implements RowMapper<Charity> {
    public Charity mapRow(ResultSet resultSet, int i) throws SQLException {
        Charity charity = new Charity();
        charity.setCharityId(resultSet.getInt("charity_id"));
        charity.setDonatedAmount(resultSet.getBigDecimal("donated_price"));
        charity.setDate(resultSet.getDate("date").toLocalDate());


        CharityTypeMapper charityTypeMapper = new CharityTypeMapper();
        charity.setCharityType(charityTypeMapper.mapRow(resultSet, i));

        MemberMapper memberMapper = new MemberMapper();
        charity.setMember(memberMapper.mapRow(resultSet, i));

        CandidateMapper candidateMapper = new CandidateMapper();
        charity.setCandidate(candidateMapper.mapRow(resultSet, i));

        PaymentMapper paymentMapper = new PaymentMapper();
        charity.setPayment(paymentMapper.mapRow(resultSet, i));

        return charity;
    }
}
