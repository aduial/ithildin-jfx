package aduial.ithildin.repository;

import aduial.ithildin.entity.EntryNoteView;
import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by luthien on 18/02/2021.
 */
public interface EntryNoteRepo extends CrudRepository<EntryNoteView, Long>{

    ObservableList<EntryNoteView> findByEntryId(Long entryId);

}
