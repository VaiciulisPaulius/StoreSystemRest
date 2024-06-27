package lt.viko.eif.pvaiciulis.StoreSystemRest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.PersonRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.exception.PersonException;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles requests and responses for person.
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Operation(summary="get all people")
    @ApiResponse(responseCode="200", description = "Found all people", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
    })

    /** GET request that retrieves all the people.
     *
     * @return list of people
     */
    @GetMapping
    public List<Person> all() {
        return personRepository.findAll();
    }

    //@Operation(summary="Create a new quantifiable product")
    @ApiResponse(responseCode="201", description = "person created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * POST request that creates a new Person.
     * @param newPerson a new person object that will create the person
     * @return an instance of the person that has been updated.
     */
    Person create(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

    @Operation(summary="Update an existing person")
    @ApiResponse(responseCode="200", description = "person updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
    })
    @PutMapping("/{id}")
    /**
     * PUT request that updates an existing person.
     * @param newPerson a new person object that will update the person
     * @param id the identifier of the retrieved person
     * @return an instance of the person that has been updated.
     */
    Person update(@RequestBody Person newPerson, @PathVariable int id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setPhoneNumber(newPerson.getPhoneNumber());
                    person.setBirthDate(newPerson.getBirthDate());
                    return personRepository.save(person);
                })
                .orElseThrow(() -> new PersonException(id));
    }

    @Operation(summary="Delete a person")
    @ApiResponse(responseCode="204", description = "person deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * DELETE request that deletes a person based on id
     * @param id the identifier of the retrieved person
     */
    void delete(@PathVariable int id) {
        personRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    /**
     * @param id the identifier of the retrieved person
     * @return a person
     */
    Person one(@PathVariable int id) {
        Person person= personRepository.findById(id)
                .orElseThrow(() -> new PersonException(id));
        return person;
    }
}

