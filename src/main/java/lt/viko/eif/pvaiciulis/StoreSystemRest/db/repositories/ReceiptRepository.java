package lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories;

import lt.viko.eif.pvaiciulis.StoreSystemRest.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Receipt repository that performs DB operations with Receipt table
 */
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findAll();
}
