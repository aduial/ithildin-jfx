package aduial.ithildin.repository;

import aduial.ithildin.entity.RefDerivView;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RefDerivRepo extends CrudRepository<RefDerivView, Long>{

    ArrayList<RefDerivView> findByEntryId(Long entryIdFrom);

}
