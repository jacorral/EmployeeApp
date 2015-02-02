/*
 * Supervisor Class Description
 * Supervisor class extends the Employee class by adding three properties
 * department, numberOfEmployees and an instance of Secretary, while inheriting
 * all of the Employee properties.
 * equals() and toString() methods are overwritten
 */
package assignment1;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty ;

/**
 * @author Jose Corral
 */
public class Supervisor extends Employee {
    private final StringProperty department =
            new SimpleStringProperty(this, "department");
    private final StringProperty numberofemployees = 
            new SimpleStringProperty(this, "numberofemployes");
    private Secretary secretary;
    //private final ObjectProperty<Secretary> secretary = new SimpleObjectProperty<Secretary>(this, "secretary");
    

    /*
     * 1st constructor
     *
     * @param firstName, lastName, department, numberOfEmployees, Secretary are the parameters for the Supervisor constructor
     * @return Supervisor object
     */
    
    public Supervisor(){
        super();
        this.secretary = new Secretary();
        this.setTitle("Supervisor");
    }
    

    public Supervisor(String first, String last, String department, String numberofemployees, Secretary secretary) {
        super(first,last);
        this.setDepartment(department);
        this.setNumberOfEmployees(numberofemployees);
        this.secretary = secretary;
        this.setTitle("Supervisor");
    }

   

    /*
     * getter method for the department property
     *
     * @param none
     * @return department - string value of department
     */

    public String getDepartment() {
        return department.get();
    }

    /*
     * getter method for the numberOfEmployees property
     *
     * @param none
     * @return numberOfEmployees - string value of numberOfEmployees
     */
    public String getNumberOfEmployees() {
        return numberofemployees.get();
    }

    /*
     * getter method for the secretary property
     *
     * @param none
     * @return secretary - instance of Secretary object in Supervisor object
     */
    public Secretary getSecretary() {
        return this.secretary;
    }


    /*
     * setter method for the department property
     *
     * @param department - string value of department property to be set
     * @return void - no return value
     */

    public void setDepartment(String department) {
        this.department.set(department); 
    }

    /*
     * setter method for the number of employees
     *
     * @param numberOfEmployees - string value of numberOfEmployees
     * @return void - no return value
     */
    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberofemployees.set(numberOfEmployees);
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }


    /*
     * Implementations of the toString() method (Override)
     */

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstname.get() + '\'' +
                ", lastName='" + lastname.get() + '\'' +
                ", phoneNumber='" + phone.get() + '\'' +
                ", address='" + address.get() + '\'' +
                ", ID='" + id + '\'' +
                ", title='" + title.get() + '\'' +
                ", salary='" + salary.get() + '\'' +
                ", department='" + department.get() + '\'' +
                ", secretary='" + secretary.getFirstname() + " " + secretary.getLast() + '\'' +
                '}'; 
        
        
        
        /*
        return "Supervisor{" +
                "firstName='" + this.getFirstname() + '\'' +
                ", lastName='" + this.getLast() + '\'' +
                ", phoneNumber='" + this.getPhone() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", ID='" + this.getId() + '\'' +
                ", title='" + this.getTitle() + '\'' +
                ", salary='" + this.getSalary() + '\'' +
                ", department='" + department + '\'' +
                ", numberOfEmployees='" + numberofemployees + '\'' +
                ", secretary='" + this.secretary.getFirstname() + " " + this.secretary.getLast() + '\''+
                "} ";// + super.toString(); */
    }


    /*
     * Implementations of the equals() method (Override)
     */


    @Override
    public boolean equals(Object o) {
        return this.getId() == ((Employee) o).getId();
    }


}
