package lt.viko.eif.pvaiciulis.StoreSystemRest.service;

import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.EntityProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.Discount;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.EntityProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Performs EntityProduct related operations.
 */
@Service
public class EntityProductService {
    @Autowired
    private EntityProductRepository entityProductRepository;

    /** retrieves a product from the database based on its barcode.
     *
     * @param barCode the barcode of the products.
     * @return an entity product.
     */
    public EntityProduct getProduct(int barCode) {
        Optional<EntityProduct> product = entityProductRepository.findByBarCode(barCode);
        return product.orElse(null);
    }

    /** Determines if an entity object has a discount or not.
     *
     * @param product the entity product to check
     * @return the discount if it exists on the product.
     */
    public Discount checkDiscount(EntityProduct product) {
        Discount discount = entityProductRepository.findByDiscountedProduct(product);
        return discount == null ? null : discount;
    }

    /** determines if the specified barcode exists in the entity product table
     *
     * @param barCode the barcode of the entity product.
     * @return a true/false statement if the specified barcode exists.
     */
    public boolean barcodeExists(int barCode) {
        return entityProductRepository.existsByBarCode(barCode);
    }


}
