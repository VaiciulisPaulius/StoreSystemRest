package lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product;

import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.Discount;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.EntityProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * EntityProduct repository that performs DB operations with entity product table
 */
public interface EntityProductRepository extends JpaRepository<EntityProduct, Integer> {
    Optional<EntityProduct> findByBarCode(int barCode);

    @Query("SELECT d FROM Discount d WHERE d.discountedProduct.id = ?1")
    Discount findByDiscountedProduct(EntityProduct product);

    boolean existsByBarCode(int barCode);


}
