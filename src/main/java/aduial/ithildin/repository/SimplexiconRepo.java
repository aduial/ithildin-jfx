package aduial.ithildin.repository;

import aduial.ithildin.entity.SimpLexicon;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface SimplexiconRepo extends CrudRepository<SimpLexicon, Long>{

    ObservableList<SimpLexicon> findByGlossAndLanguageId(String gloss, Long langId);

}
