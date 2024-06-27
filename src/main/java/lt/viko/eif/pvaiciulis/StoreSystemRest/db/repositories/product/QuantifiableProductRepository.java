package lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product;

import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.QuantifiableProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * QuantifiableProduct repository that performs DB operations with quantifiableProduct table
 */
public interface QuantifiableProductRepository  extends JpaRepository<QuantifiableProduct, Integer> {
}
