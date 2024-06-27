package lt.viko.eif.pvaiciulis.StoreSystemRest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount.DiscountRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.exception.DiscountException;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles requests and responses for discounts
 */
@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @Operation(summary="get all discounts")
    @ApiResponse(responseCode="200", description = "Found all discounts", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class))
    })

    /** GET request that retrieves all the discounts.
     * @return list of discounts
     */
    @GetMapping
    public List<Discount> all() {
        return discountRepository.findAll();
    }

    @Operation(summary="Create a new quantifiable product")
    @ApiResponse(responseCode="201", description = "Discount created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * POST request that creates a new discount.
     * @param newDiscount a new discount object that will create the discount
     * @return an instance of the discount that has been created.
     */
    Discount create(@RequestBody Discount newDiscount) {
        return discountRepository.save(newDiscount);
    }

    @Operation(summary="Update an existing discount")
    @ApiResponse(responseCode="200", description = "discount updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class))
    })
    @PutMapping("/{id}")
    /**
     * PUT request that updates an existing discount.
     * @param newDiscount a new discount object that will update the discount
     * @param id the identifier of the retrieved discount
     * @return an instance of the discount that has been updated.
     */
    Discount update(@RequestBody Discount newDiscount, @PathVariable int id) {
        return discountRepository.findById(id)
                .map(discount -> {
                    discount.setCategory(newDiscount.getCategory());
                    discount.setDiscountPrice(newDiscount.getDiscountPrice());
                    discount.setPercentOff(discount.getPercentOff());
                    return discountRepository.save(discount);
                })
                .orElseThrow(() -> new DiscountException(id));
    }

    @Operation(summary="Delete a discount")
    @ApiResponse(responseCode="204", description = "discount deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * DELETE request that deletes a discount based on id
     * @param id the identifier of the retrieved discount
     */
    void delete(@PathVariable int id) {
        discountRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    /** GET request that retrieves an individual discount
     *
     * @param id the identifier of the retrieved discount
     * @return a discount
     */
    Discount one(@PathVariable int id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new DiscountException(id));
        return discount;
    }
}

