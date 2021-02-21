package aduial.ithildin.repository;

import aduial.ithildin.entity.Ref;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RefRepo extends CrudRepository<Ref, Long>{

    ArrayList<Ref> findRefsByEntryId(Long entryId);

}
