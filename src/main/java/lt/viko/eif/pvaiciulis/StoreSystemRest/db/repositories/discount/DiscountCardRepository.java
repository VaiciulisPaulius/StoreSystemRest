package lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount;

import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DiscountCard repository that performs DB operations with discount card table
 */
public interface DiscountCardRepository  extends JpaRepository<DiscountCard, Integer> {
}
