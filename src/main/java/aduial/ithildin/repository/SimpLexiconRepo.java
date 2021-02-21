package aduial.ithildin.repository;

import aduial.ithildin.entity.SimpLexicon;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SimpLexiconRepo extends CrudRepository<SimpLexicon, Long>{

    ArrayList<SimpLexicon> findByGlossAndLanguageId(String gloss, Long langId);

    ArrayList<SimpLexicon> findByFormAndLanguageId(String form, Long langId);

}
