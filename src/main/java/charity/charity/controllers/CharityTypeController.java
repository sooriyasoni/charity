package charity.charity.controllers;

import charity.charity.domain.CharityTypeService;
import charity.charity.models.CharityType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/charityType")
public class CharityTypeController {
    private final CharityTypeService service;

    public CharityTypeController(CharityTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<CharityType> findAll() {
        return service.findAll();
    }

    @GetMapping("/{charityTypeId}")
    public CharityType findById(@PathVariable int charityTypeId) {
        return service.findById(charityTypeId);
    }

}
