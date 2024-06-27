package lt.viko.eif.pvaiciulis.StoreSystemRest.service;

import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount.DiscountCardRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.EntityProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * performs discount card related operations.
 */
@Service
public class DiscountCardService {
    @Autowired
    private DiscountCardRepository discountCardRepository;

    @Autowired
    private EntityProductService entityProductService;

    /** Generates an unique barcode for the discount card.
     *
     * @return integer that is unique among all entity products.
     */
    public int generateUniqueBarcode() {
        int barcode;
        boolean evaluate;
        do {
            barcode = (int) (Math.random() * (DiscountCard.DISCOUNT_CARD_MAX_BARCODE - DiscountCard.DISCOUNT_CARD_MIN_BARCODE + 1)) + DiscountCard.DISCOUNT_CARD_MIN_BARCODE;
            evaluate = entityProductService.barcodeExists(barcode);
        } while (evaluate);
        return barcode;
    }
}
