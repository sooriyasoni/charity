package charity.charity.controllers;

import charity.charity.domain.MemberService;
import charity.charity.domain.Result;
import charity.charity.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> findAll() {
        return service.findAll();
    }

    @GetMapping("/{memberId}")
    public Member findById(@PathVariable int memberId) {
        return service.findById(memberId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Member member)  {
        Result<Member> result = service.add(member);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Object> update(@PathVariable int memberId, @RequestBody Member member) throws Exception {
        if (memberId != member.getMemberId()) {
            throw new RequestConflictException();
        }

        Result<Member> result = service.update(member);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new RecordNotFoundException();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteById(@PathVariable int memberId) throws Exception {
        if (service.deleteById(memberId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new RecordNotFoundException();
    }

}
