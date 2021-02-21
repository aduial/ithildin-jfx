package aduial.ithildin.repository;

import aduial.ithildin.entity.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LanguageRepo extends CrudRepository<Language, Long>{

    ArrayList<Language> findLanguagesByIdGreaterThanAndParentIdIsNotNull(Long id);

}
