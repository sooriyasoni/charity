package charity.charity.controllers;

import charity.charity.domain.MemberService;
import charity.charity.domain.PaymentService;
import charity.charity.domain.Result;
import charity.charity.models.Member;
import charity.charity.models.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/payment")

public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Payment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{paymentId}")
    public Payment findById(@PathVariable int paymentId) {
        return service.findById(paymentId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Payment payment) throws Exception {
        Result<Payment> result = service.add(payment);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        throw new Exception();
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Object> update(@PathVariable int paymentId, @RequestBody Payment payment) throws Exception {
        if (paymentId != payment.getPaymentId()) {
            throw new IllegalArgumentException();
        }

        Result<Payment> result = service.update(payment);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        throw new RecordNotFoundException();
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deleteById(@PathVariable int paymentId) throws Exception {
        if (service.deleteById(paymentId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new RecordNotFoundException();
    }
}