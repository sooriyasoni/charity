package charity.charity.data.mapper;

import charity.charity.models.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(resultSet.getInt("payment_id"));
        payment.setType(resultSet.getString("type"));
        payment.setStatus(resultSet.getString("status"));
        return payment;
    }
}
