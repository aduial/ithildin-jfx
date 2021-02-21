package aduial.ithildin.repository;

import aduial.ithildin.entity.RefCognateView;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RefCognateRepo extends CrudRepository<RefCognateView, Long>{

    ArrayList<RefCognateView> findByEntryId(Long entryId);

}