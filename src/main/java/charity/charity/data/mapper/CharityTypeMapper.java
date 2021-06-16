package charity.charity.data.mapper;

import charity.charity.models.CharityType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharityTypeMapper implements RowMapper<CharityType> {
    @Override
    public CharityType mapRow(ResultSet resultSet, int i) throws SQLException {
        CharityType charityType = new CharityType();
        charityType.setCharityTypeId(resultSet.getInt("charity_type_id"));
        charityType.setType(resultSet.getString("type"));
        charityType.setDesciprtion(resultSet.getString("description"));
        return charityType;
    }
}
