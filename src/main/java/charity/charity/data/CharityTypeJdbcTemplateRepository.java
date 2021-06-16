package charity.charity.data;

import charity.charity.data.mapper.CharityTypeMapper;
import charity.charity.models.CharityType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CharityTypeJdbcTemplateRepository implements CharityTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    public CharityTypeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CharityType> findAll() {
        final String sql = "select charity_type_id, type, description "
                + "from charity_type limit 1000;";
        return jdbcTemplate.query(sql, new CharityTypeMapper());
    }

    @Override
    @Transactional
    public CharityType findById(int charityTypeId) {

        final String sql = "select charity_type_id, type, description "
                + "from charity_type "
                + "where charity_type_id = ?;";

        CharityType charityType = jdbcTemplate.query(sql, new CharityTypeMapper(), charityTypeId).stream()
                .findFirst().orElse(null);


        return charityType;
    }

}
