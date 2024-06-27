package lt.viko.eif.pvaiciulis.StoreSystemRest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.QuantifiableProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.exception.QuantifiableProductException;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.QuantifiableProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles requests and responses for quantifiable products.
 */
@RestController
@RequestMapping("/quantifiableProduct")
public class QuantifiableProductController {
    @Autowired
    private QuantifiableProductRepository quantifiableProductRepository;

    @Operation(summary="get all quantifiable products")
    @ApiResponse(responseCode="200", description = "Found all employees", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = QuantifiableProduct.class))
    })
    /** GET request that retrieves all the quantifiable products.
     *
     * @return list of quantifiable products
     */
    @GetMapping
    public List<QuantifiableProduct> all() {
        return quantifiableProductRepository.findAll();
    }

    //@Operation(summary="Create a new quantifiable product")
    @ApiResponse(responseCode="201", description = "Quantifiable product created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = QuantifiableProduct.class))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * POST request that creates a new Quantifiable product
     * @param newQuantifiableProduct a new quantifiable product object
     * @return an instance of the quantifiable product that has been created.
     *
     */
    QuantifiableProduct create(@RequestBody QuantifiableProduct newQuantifiableProduct) {
        return quantifiableProductRepository.save(newQuantifiableProduct);
    }

    @Operation(summary="Update an existing quantifiable product")
    @ApiResponse(responseCode="200", description = "Quantifiable product updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = QuantifiableProduct.class))
    })
    @PutMapping("/{id}")
    /**
     * PUT request that updates an existing quantifiable product.
     * @param newQuantifiableProduct a new quantifiable product object that will update the discount card
     * @param id the identifier of the retrieved quantifiable product.
     * @return an instance of the quantifiable product that has been updated.
     */
    QuantifiableProduct update(@RequestBody QuantifiableProduct newQuantifiableProduct, @PathVariable int id) {
        return quantifiableProductRepository.findById(id)
                .map(quantifiableProduct -> {
                    quantifiableProduct.setName(newQuantifiableProduct.getName());
                    // Set other properties as needed
                    return quantifiableProductRepository.save(quantifiableProduct);
                })
                .orElseThrow(() -> new QuantifiableProductException(id));
    }

    @Operation(summary="Delete a quantifiable product")
    @ApiResponse(responseCode="204", description = "Quantifiable product deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * DELETE request that deletes a quantifiable product based on id
     * @param id the identifier of the retrieved quantifiable product.
     */
    void delete(@PathVariable int id) {
        quantifiableProductRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    /** GET request that retrieves an individual quantifiable product.
     *
     * @param id the identifier of the retrieved quantifiable product.
     * @return a quantifiable product.
     */
    QuantifiableProduct one(@PathVariable int id) {
        QuantifiableProduct quantifiableProduct = quantifiableProductRepository.findById(id)
                .orElseThrow(() -> new QuantifiableProductException(id));
        return quantifiableProduct;
    }
}
