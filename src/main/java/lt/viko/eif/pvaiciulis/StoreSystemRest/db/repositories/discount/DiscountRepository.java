package lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount;

import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Discount repository that performs DB operations with discount table
 */
public interface DiscountRepository  extends JpaRepository<Discount, Integer> {
}
