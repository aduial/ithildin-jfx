package aduial.ithildin.repository;

import aduial.ithildin.entity.RefInflectView;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RefInflectRepo extends CrudRepository<RefInflectView, Long>{

    ArrayList<RefInflectView> findByEntryId(Long entryId);

}
