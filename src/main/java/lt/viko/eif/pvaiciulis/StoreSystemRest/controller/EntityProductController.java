package lt.viko.eif.pvaiciulis.StoreSystemRest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.EntityProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.exception.EntityProductException;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.EntityProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles requests and responses for entity product
 */
@RestController
@RequestMapping("/entityProduct")
public class EntityProductController {
    @Autowired
    private EntityProductRepository entityProductRepository;

    @Operation(summary="get all entity products")
    @ApiResponse(responseCode="200", description = "Found all entity products", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityProduct.class))
    })

    /** GET request that retrieves all the entity products.
     * @return list of entity products
     */
    @GetMapping
    public List<EntityProduct> all() {
        return entityProductRepository.findAll();
    }

    //@Operation(summary="Create a new quantifiable product")
    @ApiResponse(responseCode="201", description = "entity product created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityProduct.class))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * POST request that creates a new EntityProduct.
     * @param newEntityProduct a new entity product object that will create the product
     * @return an instance of the entity product that has been created.
     */
    EntityProduct create(@RequestBody EntityProduct newEntityProduct) {
        return entityProductRepository.save(newEntityProduct);
    }

    @Operation(summary="Update an existing entity product")
    @ApiResponse(responseCode="200", description = "entity product updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityProduct.class))
    })
    @PutMapping("/{id}")
    /**
     * PUT request that updates an existing entity product.
     * @param newEntityProduct a new entity product object that will update the product
     * @param id the identifier of the retrieved entity product.
     * @return an instance of the entity product that has been updated.
     */
    EntityProduct update(@RequestBody EntityProduct newEntityProduct, @PathVariable int id) {
        return entityProductRepository.findById(id)
                .map(entityProduct -> {
                    entityProduct.setName(newEntityProduct.getName());
                    entityProduct.setPrice(newEntityProduct.getPrice());
                    entityProduct.setBarCode(newEntityProduct.getBarCode());
                    // Set other properties as needed
                    return entityProductRepository.save(entityProduct);
                })
                .orElseThrow(() -> new EntityProductException(id));
    }

    @Operation(summary="Delete a entity product")
    @ApiResponse(responseCode="204", description = "entity product deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * DELETE request that deletes an entity product based on id
     * @param id the identifier of the retrieved entity product.
     */
    void delete(@PathVariable int id) {
        entityProductRepository.deleteById(id);
    }

    /** GET request that retrieves an individual entity product.
     *
     * @param id the identifier of the retrieved entity product.
     * @return a receipt
     */
    @GetMapping("/{id}")
    EntityProduct one(@PathVariable int id) {
        EntityProduct entityProduct = entityProductRepository.findById(id)
                .orElseThrow(() -> new EntityProductException(id));
        return entityProduct;
    }
}

