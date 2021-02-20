package aduial.ithildin.repository;

import aduial.ithildin.entity.RefElementView;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface RefElementRepo extends CrudRepository<RefElementView, Long>{

    ObservableList<RefElementView> findByEntryId(Long entryId);

}