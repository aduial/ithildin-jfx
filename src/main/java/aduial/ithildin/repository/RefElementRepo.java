package aduial.ithildin.repository;

import aduial.ithildin.entity.RefElementView;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RefElementRepo extends CrudRepository<RefElementView, Long>{

    ArrayList<RefElementView> findByEntryId(Long entryId);

}