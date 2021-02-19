package aduial.ithildin.repository;

import aduial.ithildin.entity.Language;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepo extends CrudRepository<Language, Long>{

    ObservableList<Language> findLanguagesByIdGreaterThanAndParentIdIsNotNull(Long id);

}
