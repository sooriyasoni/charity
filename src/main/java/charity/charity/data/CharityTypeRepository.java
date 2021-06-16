package charity.charity.data;

import charity.charity.models.CharityType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CharityTypeRepository {
    List<CharityType> findAll();

    @Transactional
    CharityType findById(int charityTypeId);
}
