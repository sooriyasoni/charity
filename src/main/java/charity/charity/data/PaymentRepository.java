package charity.charity.data;

import charity.charity.models.Payment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();

    @Transactional
    Payment findById(int paymentId);

    Payment add(Payment payment);

    boolean update(Payment payment);

    @Transactional
    boolean deleteById(int paymentId);
}
