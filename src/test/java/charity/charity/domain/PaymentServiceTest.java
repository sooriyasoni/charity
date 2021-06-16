package charity.charity.domain;

import charity.charity.data.PaymentRepository;
import charity.charity.data.PaymentStatus;
import charity.charity.models.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PaymentServiceTest {

        @Autowired
        PaymentService service;

        @MockBean
        PaymentRepository repository;

        @Test
        void shouldFindPayment() {
            // pass-through test, probably not useful
            Payment expected = makePayment();
            when(repository.findById(1)).thenReturn(expected);
            Payment actual = service.findById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotAddWhenInvalid() {
            Payment payment = makePayment();
            Result<Payment> result = service.add(payment);
            assertEquals(ResultType.INVALID, result.getType());

            payment.setPaymentId(0);
            payment.setType("");
            result = service.add(payment);
            assertEquals(ResultType.INVALID, result.getType());
        }

    Payment makePayment() {
            Payment payment = new Payment();
            payment.setPaymentId(1);
            payment.setStatus(PaymentStatus.PAID.toString());
            payment.setType("Bank");
            return payment;
        }
}
