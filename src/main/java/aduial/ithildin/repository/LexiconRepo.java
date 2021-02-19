package aduial.ithildin.repository;

import aduial.ithildin.entity.Lexicon;
import org.springframework.data.repository.CrudRepository;

public interface LexiconRepo extends CrudRepository<Lexicon, Long>{

    Lexicon findOne(Long entityId);

}
