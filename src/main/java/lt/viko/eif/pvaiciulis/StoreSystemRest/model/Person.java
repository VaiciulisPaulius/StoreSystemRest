package lt.viko.eif.pvaiciulis.StoreSystemRest.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Class model that represents data about a person.
 */
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;

    /**
     * Returns a string representation of the object 'Person'.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\tfirst name: %s\n" + "\t\t\tlast name: %s\n" + "\t\t\tphoneNumber: %s\n" + "\t\t\tbirth date: %s",
                firstName, lastName, phoneNumber, birthDate);
    }

    /**
     *
     * @param firstName     the first name of the person
     * @param lastName      the last name of the person
     * @param phoneNumber   the phone number of the person
     * @param birthDate     the birthdate of the person
     */

    public Person(String firstName, String lastName, String phoneNumber, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    /**
     * Default constructor for Person
     */
    public Person() {}

    /** Returns the full name of the person
     *
     * @return the full name of the person
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /** Gets the first name of the person
     *
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /** Sets the first name of the person
     *
     * @param firstName first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** Gets the last name of the person
     *
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /** sets the last name of the person
     *
     * @param lastName the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** Gets the phone number of the person
     *
     * @return the phone number of the person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** sets the phone number of the person
     *
     * @param phoneNumber the phone number of the person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** Gets the birthdate of the person
     *
     * @return the birthdate of the person
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /** sets the birthdate of the person
     *
     * @param birthDate the birthdate of the person
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
