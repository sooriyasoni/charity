package charity.charity.controllers;

import charity.charity.domain.CharityService;
import charity.charity.domain.CharityTypeService;
import charity.charity.domain.Result;
import charity.charity.models.Candidate;
import charity.charity.models.Charity;
import charity.charity.models.CharityType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/charity")
public class CharityController {

    private final CharityService service;

    public CharityController(CharityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Charity> findAll() {
        return service.findAll();
    }

    @GetMapping("/{memberId}")
    public List<Charity> findByMember(@PathVariable int memberId) {
        return service.findMembersCharity(memberId);
    }

    @GetMapping("/{candidateId}")
    public List<Charity> findByCandidate (@PathVariable int candidateId) {
        return service.findCandidatesCharity(candidateId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Charity charity) throws Exception {
        Result<Charity> result = service.add(charity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        throw new Exception();
    }

}
