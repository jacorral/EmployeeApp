/*
 * Secretary Class Description
 * Secretary extends the Employee class by adding two properties
 * office and status, while inheriting all of the Employee's properties
 * equals() and toString() methods are overwritten
 *
 */
package assignment1;
//
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Jose Corral
 */
public class Secretary extends Employee {
    private final StringProperty office =
            new SimpleStringProperty(this,"office");
    private final StringProperty status =
            new SimpleStringProperty(this,"status");


    /*
     * 1st constructor
     *
     * @param firstName, lastName, office, status are the parameters for the Secretary constructor
     * @return Secretary object
     *
     */

    public Secretary() {
        super();
        this.setTitle("Secretary");
    }
    /*
     * 2nd constructor
     *
     * @param firstName, lastName, id, office, status are the parameters for the Secretary constructor
     * @return Secretary object
     *
     */

    public Secretary(String first, String last, String office, String status) {
        super(first, last);
        this.office.set(office);
        this.status.set(status);
        this.setTitle("Secretary");
    }

    /*
     * 3rd constructor
     *
     * @param firstName, lastName, id, title, office, status are the parameters for the Secretary constructor
     * @return Secretary object
     *
     */

    public Secretary(String first, String last, String office) {
        super(first, last);
        this.office.set(office);
        this.setTitle("Secretary");
    }


    /*
     * Getter method for the office property
     *
     *@param args unused
     * @return office string value of office
     */
    public String getOffice() {
        return office.get();
    }

    /*
     * Getter method for the status property
     *
     *@param args unused
     *@return string value of status
     */
    public String getStatus() {
        return status.get();
    }

    //Setters\

    /* Setter method for setting the office location for the
     * secretary object
     *@param office this is the parameter for the setOffice method
     *@return void no value is returned
     */

    public void setOffice(String office) {
        this.office.set(office);
    }
    /*
     * Setter method for setting the status for the Secretary object
     * @param status this is the the parameter for the setStatus method
     * @return void no value is returned
     */

    public void setStatus(String status) {
        this.status.set(status);
    }


    /*
     * Implementation of the toString() method (Override)
     */

    @Override
    public String toString() {
        return "Secretary{" +
                 "firstName='" + firstname.get() + '\'' +
                ", lastName='" + lastname.get() + '\'' +
                ", phoneNumber='" + phone.get() + '\'' +
                ", address='" + address.get() + '\'' +
                ", ID='" + id + '\'' +
                ", title='" + title.get() + '\'' +
                ", salary='" + salary.get() + '\'' +
                '}';// + super.toString();
    }


    /*
     * Implementations of the equals() method (Override)
     */


    @Override
    public boolean equals(Object o) {

        return this.getId() == ((Employee) o).getId();
    }


}
