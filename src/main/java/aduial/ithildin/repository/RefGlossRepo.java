package aduial.ithildin.repository;

import aduial.ithildin.entity.RefGlossView;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by luthien on 18/02/2021.
 */
public interface RefGlossRepo extends CrudRepository<RefGlossView, Long>{

    ObservableList<RefGlossView> findByEntryId(Long entryId);

}
