package charity.charity.data;

import charity.charity.data.mapper.PaymentMapper;
import charity.charity.models.Payment;
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
public class PaymentJdbcTemplateRepository implements PaymentRepository {
    private final JdbcTemplate jdbcTemplate;

    public PaymentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Payment> findAll() {
        final String sql = "select payment_id, type, status "
                + "from payment limit 1000;";
        return jdbcTemplate.query(sql, new PaymentMapper());
    }

    @Override
    @Transactional
    public Payment findById(int paymentId) {

        final String sql = "select payment_id, type, status "
                + "from payment "
                + "where payment_id = ?;";

        Payment payment = jdbcTemplate.query(sql, new PaymentMapper(), paymentId).stream()
                .findFirst().orElse(null);


        return payment;
    }

    @Override
    public Payment add(Payment payment) {

        final String sql = "insert into payment ( type, status) "
                + " values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, payment.getType());
            ps.setString(2, payment.getStatus());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        payment.setPaymentId(keyHolder.getKey().intValue());
        return payment;
    }

    @Override
    public boolean update(Payment payment) {

        final String sql = "update payment set "
                + "type = ?, "
                + "status = ? "
                + "where payment_id = ?;";

        return jdbcTemplate.update(sql,
                payment.getType(),
                payment.getStatus(),
                payment.getPaymentId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int paymentId) {
        jdbcTemplate.update("delete from charity where payment_id = ?;", paymentId);
        int result=jdbcTemplate.update("delete from payment where payment_id = ?", paymentId);
        boolean deleted =  result> 0;
        return deleted;
    }
}


