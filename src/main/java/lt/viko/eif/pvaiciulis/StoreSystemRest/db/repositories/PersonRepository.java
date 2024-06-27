package lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories;

import lt.viko.eif.pvaiciulis.StoreSystemRest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person repository that performs DB operations with Person table
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
