package aduial.ithildin.repository;

import aduial.ithildin.entity.RefInflectView;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface RefInflectRepo extends CrudRepository<RefInflectView, Long>{

    ObservableList<RefInflectView> findByEntryId(Long entryId);

}
