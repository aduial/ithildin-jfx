package aduial.ithildin.repository;

import aduial.ithildin.entity.RefGlossView;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by luthien on 18/02/2021.
 */
public interface RefGlossRepo extends CrudRepository<RefGlossView, Long>{

    ArrayList<RefGlossView> findByEntryId(Long entryId);

}
