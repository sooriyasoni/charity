package charity.charity.data;

import charity.charity.models.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

public class PaymentJdbcTemplateRepositoryTest {
    @Autowired
    PaymentJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Payment> payments = repository.findAll();
        assertNotNull(payments);

        assertTrue(payments.size() >= 3);
    }

    @Test
    void shouldFindSuccessPayments() {
        Payment payment = repository.findById(1);
        assertEquals(1, payment.getPaymentId());
        assertEquals("credit_card", payment.getType());
    }

    @Test
    void shouldAdd() {
        // all fields
        Payment payment = makePayment();
        payment.setPaymentId(0);
        Payment actual = repository.add(payment);
        assertNotNull(actual);
        assertEquals(payment.getStatus(), actual.getStatus());
    }

    @Test
    void shouldUpdate() {
        Payment payment = makePayment();
        payment.setPaymentId(3);
        assertTrue(repository.update(payment));
        payment.setPaymentId(13);
        assertFalse(repository.update(payment));
    }


    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(10));
    }

    private Payment makePayment() {
        Payment payment = new Payment();
        payment.setPaymentId(3);
        payment.setStatus("Success");
        payment.setType("Gpay");
        return payment;
    }
}