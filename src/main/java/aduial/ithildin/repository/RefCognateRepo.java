package aduial.ithildin.repository;

import aduial.ithildin.entity.RefCognateView;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

public interface RefCognateRepo extends CrudRepository<RefCognateView, Long>{

    ObservableList<RefCognateView> findByEntryId(Long entryId);

}