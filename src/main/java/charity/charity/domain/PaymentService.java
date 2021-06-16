package charity.charity.domain;

import charity.charity.data.PaymentRepository;
import charity.charity.models.Payment;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> findAll() {
        return repository.findAll();
    }

    public Payment findById(int paymentId) {
        return repository.findById(paymentId);
    }

    public Result<Payment> add(Payment payment) {
        Result<Payment> result = validate(payment);

        if (!result.isSuccess()) {
            return result;
        }

        if (payment.getPaymentId() != 0) {
            result.addMessage("paymentId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        payment = repository.add(payment);
        result.setPayload(payment);
        return result;
    }

    public Result<Payment> update(Payment payment) {
        Result<Payment> result = validate(payment);
        if (!result.isSuccess()) {
            return result;
        }

        if (payment.getPaymentId() <= 0) {
            result.addMessage("paymentId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(payment)) {
            String msg = String.format("paymentId: %s, not found", payment.getPaymentId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int paymentId) {
        return repository.deleteById(paymentId);
    }

    private Result<Payment> validate(Payment payment) {
        Result<Payment> result = new Result<Payment>();
        if (payment == null) {
            result.addMessage("payment cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(payment.getType())) {
            result.addMessage("type is required", ResultType.INVALID);
        }
        return result;
    }
}
