package aduial.ithildin.repository;

import aduial.ithildin.entity.Simplexicon;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface SimplexiconRepo extends CrudRepository<Simplexicon, Long>{

    ObservableList<Simplexicon> findByGlossAndLanguageId(String gloss, Long langId);

}
