package aduial.ithildin.repository;

import aduial.ithildin.entity.RefDerivView;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface RefDerivRepo extends CrudRepository<RefDerivView, Long>{

    ObservableList<RefDerivView> findByEntryId(Long entryId);

}
