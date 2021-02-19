package aduial.ithildin.repository;

import aduial.ithildin.entity.SpeechFormView;
import org.springframework.data.repository.CrudRepository;

public interface SpeechFormRepo extends CrudRepository<SpeechFormView, Long>{

    SpeechFormView getOne(Long id);

}
