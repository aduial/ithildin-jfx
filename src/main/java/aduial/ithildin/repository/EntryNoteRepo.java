package aduial.ithildin.repository;

import aduial.ithildin.entity.EntryNoteView;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by luthien on 18/02/2021.
 */
public interface EntryNoteRepo extends CrudRepository<EntryNoteView, Long>{

    ArrayList<EntryNoteView> findByEntryId(Long entryId);

}
