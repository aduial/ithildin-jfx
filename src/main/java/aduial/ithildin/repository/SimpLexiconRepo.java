package aduial.ithildin.repository;

import aduial.ithildin.entity.SimpLexicon;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SimpLexiconRepo extends CrudRepository<SimpLexicon, Long>{

    ArrayList<SimpLexicon> findByGlossContainingAndLanguageId(String gloss, Long langId);

    ArrayList<SimpLexicon> findByFormContainingAndLanguageId(String form, Long langId);

}
