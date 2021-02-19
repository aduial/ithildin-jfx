package aduial.ithildin.repository;

import aduial.ithildin.entity.Ref;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface RefRepo extends CrudRepository<Ref, Long>{

    ObservableList<Ref> findRefsByEntryId(Long entryId);

}
