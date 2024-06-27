package lt.viko.eif.pvaiciulis.StoreSystemRest.model;

import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.EntityProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.Discount;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.DiscountCard;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.EntityProduct;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.QuantifiableProduct;

import jakarta.persistence.*;
import lt.viko.eif.pvaiciulis.StoreSystemRest.service.EntityProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Class model that represents data about a receipt, including the products it has, and discount card..
 */
@Entity
@Table(name = "Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")

    private int id;

    @OneToMany(targetEntity = QuantifiableProduct.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)

    private List<QuantifiableProduct> products = new ArrayList<>();

    @OneToOne(targetEntity = DiscountCard.class)
    private DiscountCard discountCard;

    /**
     * Default receipt constructor
     */
    public Receipt() {
    }

    private LocalDateTime timeOfPurchase;
    private double subtotal;

    private double total;

    /**
     * Returns a string representation of the object 'Receipt'.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("Receipt:\n" + "\tid: %s\n" + "\tProducts: \n%s\n" + "\tDiscount card: \n%s\n" + "\tTime of purchase: %s\n" + "\tsubtotal: %s\n" + "\ttotal: %s\n",
                id, constructProductsList(), discountCard, timeOfPurchase.toString(), subtotal, total);
    }

    /** Constructs a readable text of all the products in the receipt.
     *
     * @return text of all the products in the receipt.
     */
    private String constructProductsList() {
        String result = "";
        for(QuantifiableProduct product : products)
            result += product;
        return result;
    }

    /** Gets all the quantifiable products from the receipt.
     *
     * @return quantifiable products from the receipt.
     */
    public List<QuantifiableProduct> getProducts() {
        return products;
    }

    /** Sets all the quantifiable products in one go.
     *
     * @param products list of quantifiable products.
     */
    public void setProducts(List<QuantifiableProduct> products) {
        this.products = products;
    }

    /** Gets the discount card of the receipt, if it was applied.
     *
     * @return discount card of the product.
     */
    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    /** Sets the discount card of the product
     *
     * @param discountCard discount card of the product
     */
    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    /** gets the timestamp when the receipt has finished its transaction.
     *
     * @return timestamp of the receipt.
     */
    public LocalDateTime getTimestamp() {
        return timeOfPurchase;
    }

    /** sets the timestamp of the receipt, indicating it has finished the transaction.
     *
     * @param timestamp timestamp of the receipt.
     */
    public void setTimestamp(LocalDateTime  timestamp) {
        this.timeOfPurchase = timestamp;
    }

    /** Gets the subtotal price of the receipt excluding discounts.
     *
     * @return the subtotal price of the receipt.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /** Sets the subtotal price of the receipt.
     *
     * @param subtotal the subtotal price of the receipt.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /** Gets the total price of the receipt including all the discounts.
     *
     * @return total price of the receipt.
     */
    public double getTotal() {
        return total;
    }

    /** Sets the total price of the receipt.
     *
     * @param total price of the receipt.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Finishes the transaction.
     */
    public void finishTransaction() {
        timeOfPurchase = LocalDateTime.now();
    }
}
