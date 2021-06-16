package charity.charity.domain;

import charity.charity.data.CharityTypeRepository;
import charity.charity.models.CharityType;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharityTypeService {
    private final CharityTypeRepository repository;

    public CharityTypeService(CharityTypeRepository repository) {
        this.repository = repository;
    }

    public List<CharityType> findAll() {
        return repository.findAll();
    }

    public CharityType findById(int charityTypeId) {
        return repository.findById(charityTypeId);
    }

}
