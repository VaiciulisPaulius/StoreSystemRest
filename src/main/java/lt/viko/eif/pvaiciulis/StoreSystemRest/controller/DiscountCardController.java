package lt.viko.eif.pvaiciulis.StoreSystemRest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount.DiscountCardRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.exception.DiscountCardException;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles requests and responses for discount cards
 */
@RestController
@RequestMapping("/discountCard")
public class DiscountCardController {
    @Autowired
    private DiscountCardRepository discountCardRepository;

    @Operation(summary="get all discount cards")
    @ApiResponse(responseCode="200", description = "Found all discount cards", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DiscountCard.class))
    })

    /** GET request that retrieves all the discount cards.
     * @return list of discount cards
     */
    @GetMapping
    public List<DiscountCard> all() {
        return discountCardRepository.findAll();
    }

    @Operation(summary="Create a new quantifiable product")
    @ApiResponse(responseCode="201", description = "discount card created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DiscountCard.class))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * POST request that creates a new discount card
     * @param newDiscountCard a new discount card object
     * @return an instance of the discount card that has been created.
     */
    DiscountCard create(@RequestBody DiscountCard newDiscountCard) {
        return discountCardRepository.save(newDiscountCard);
    }

    @Operation(summary="Update an existing discount card")
    @ApiResponse(responseCode="200", description = "discount card updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DiscountCard.class))
    })
    @PutMapping("/{id}")
    /**
     * PUT request that updates an existing discount card.
     * @param newDiscountCard a new discount card object that will update the discount card
     * @param id the identifier of the retrieved discount card.
     * @return an instance of the discount card that has been updated.
     */
    DiscountCard update(@RequestBody DiscountCard newDiscountCard, @PathVariable int id) {
        return discountCardRepository.findById(id)
                .map(discountCard -> {
                    discountCard.setName(newDiscountCard.getName());
                    discountCard.setCategory(newDiscountCard.getCategory());
                    discountCard.setPerson(newDiscountCard.getPerson());

                    return discountCardRepository.save(discountCard);
                })
                .orElseThrow(() -> new DiscountCardException(id));
    }

    @Operation(summary="Delete a discount")
    @ApiResponse(responseCode="204", description = "Quantifiable product deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * DELETE request that deletes a discount card based on id
     * @param id the identifier of the retrieved discount card.
     */
    void delete(@PathVariable int id) {
        discountCardRepository.deleteById(id);
    }

    /** GET request that retrieves an individual discount card.
     *
     * @param id the identifier of the retrieved discount card.
     * @return a discount card
     */
    @GetMapping("/{id}")
    DiscountCard one(@PathVariable int id) {
        DiscountCard discountCard = discountCardRepository.findById(id)
                .orElseThrow(() -> new DiscountCardException(id));
        return discountCard;
    }
}
