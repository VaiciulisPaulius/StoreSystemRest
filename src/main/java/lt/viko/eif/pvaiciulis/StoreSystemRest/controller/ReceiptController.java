package lt.viko.eif.pvaiciulis.StoreSystemRest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.ReceiptRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.exception.ReceiptException;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles requests and responses for receipts
 */
@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Operation(summary="get all receipts")
    @ApiResponse(responseCode="200", description = "Found all receipts", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Receipt.class))
    })
    /** GET request that retrieves all the receipts.
     *
     * @return list of receipts
     */
    @GetMapping
    public List<Receipt> all() {
        return receiptRepository.findAll();
    }

    //@Operation(summary="Create a new receipt")
    @ApiResponse(responseCode="201", description = "Quantifiable product created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Receipt.class))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * POST request that creates a new Receipt.
     * @param newReceipt a new receipt object
     * @return an instance of the discount card that has been created.
     */
    Receipt create(@RequestBody Receipt newReceipt) {
        return receiptRepository.save(newReceipt);
    }

    @Operation(summary="Update an existing receipt")
    @ApiResponse(responseCode="200", description = "receipt updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Receipt.class))
    })
    @PutMapping("/{id}")
    /**
     * PUT request that updates an existing receipt.
     * @param newReceipt a new receipt object that will update the receipt
     * @param id the identifier of the retrieved receipt.
     * @return an instance of the receipt that has been updated.
     */
    Receipt update(@RequestBody Receipt newReceipt, @PathVariable int id) {
        return receiptRepository.findById(id)
                .map(receipt -> {
                    receipt.setProducts(newReceipt.getProducts());
                    receipt.setSubtotal(newReceipt.getSubtotal());
                    receipt.setDiscountCard(newReceipt.getDiscountCard());
                    receipt.setTotal(newReceipt.getTotal());
                    receipt.setTimestamp(newReceipt.getTimestamp());
                    return receiptRepository.save(receipt);
                })
                .orElseThrow(() -> new ReceiptException(id));
    }

    @Operation(summary="Delete a receipt")
    @ApiResponse(responseCode="204", description = "receipt deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * DELETE request that deletes a receipt based on id
     * @param id the identifier of the retrieved receipt.
     */
    void delete(@PathVariable int id) {
        receiptRepository.deleteById(id);
    }

    /** GET request that retrieves an individual receipt.
     *
     * @param id the identifier of the retrieved receipt.
     * @return a receipt
     */
    @GetMapping("/{id}")
    Receipt one(@PathVariable int id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ReceiptException(id));
        return receipt;
    }
}
