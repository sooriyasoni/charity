package charity.charity.controllers;

import charity.charity.domain.CandidateService;
import charity.charity.domain.Result;
import charity.charity.models.Candidate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/candidate")
public class CandidateController {

        private final CandidateService service;

        public CandidateController(CandidateService service) {
            this.service = service;
        }

        @GetMapping
        public List<Candidate> findAll() {
            return service.findAll();
        }

        @GetMapping("/{candidateId}")
        public Candidate findById(@PathVariable int candidateId) {
            return service.findById(candidateId);
        }

        @PostMapping
        public ResponseEntity<Object> add(@RequestBody Candidate candidate) throws Exception {
            Result<Candidate> result = service.add(candidate);
            if (result.isSuccess()) {
                return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
            }
            throw new Exception();
        }

        @PutMapping("/{candidateId}")
        public ResponseEntity<Object> update(@PathVariable int candidateId, @RequestBody Candidate candidate) throws Exception {
            if (candidateId != candidate.getCandidateId()) {
                throw new RequestConflictException();
            }

            Result<Candidate> result = service.update(candidate);
            if (result.isSuccess()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            throw new Exception();
        }

        @DeleteMapping("/{candidateId}")
        public ResponseEntity<Void> deleteById(@PathVariable int candidateId) throws Exception {
            if (service.deleteById(candidateId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            throw new Exception();
        }
}
