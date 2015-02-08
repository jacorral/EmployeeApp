/*
 * This class creates employee objects with some basic information
 * and default values for the fields are given in the default constructor
 * equals() and toString() methods are overwritten
 * 
 */
package com.daBandit;

//
import java.util.Objects;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;

/**
 * @author Jose Corral
 */
@SuppressWarnings("EqualsAndHashcode")
public class Employee implements Serializable {
    
    public final transient StringProperty firstname = 
            new SimpleStringProperty(this, "firstname", "");
    public final transient StringProperty lastname =
            new SimpleStringProperty(this, "lastname", "");
    public final transient StringProperty phone =
            new SimpleStringProperty(this, "phone", "");
    public final transient StringProperty title =
            new SimpleStringProperty(this, "title", "");
    public final transient StringProperty salary =
            new SimpleStringProperty(this, "salary");
    public final transient StringProperty address =
            new SimpleStringProperty(this, "address");
    public final long id;
    private static long count = 1000;
    
    /*
    private String lastName;
    private String phoneNumber;
    private String address;
    private String id;
    private String title;
    private int salary;
    private java.util.Date dateCreated; */

    // Constructors for Employee class
    // The first constructor is the default constructor and it is called from the
    // other constructors


    public Employee() {
        this.firstname.set("John");
        this.lastname.set("Doe");
        this.phone.set("630-555-1234");
        this.salary.set("45000");
        this.title.set("Employee");
        this.address.set("123 Main St.");
        this.id = count++;
        
        /*
        this.firstname = "John";
        this.lastName = "Doe";
        this.phoneNumber = "630-555-1234";
        this.address = "123 Main St.";
        this.id = "00000";
        this.title = "Employee";
        this.salary = 45000;
        this.dateCreated = new Date(); */
    }

    public Employee(String first, String last) {
        this();
        this.firstname.set(first);
        this.lastname.set(last);
        this.title.set("Employed");
        //this.firstName = firstName;
        
    }

    public Employee(String first, String last, String title) {
        this();
        this.firstname.set(first);
        this.lastname.set(last);
        this.title.set(title);
    }
    public Employee(Employee emp){
        this.firstname.set(emp.getFirstname());
        this.lastname.set(emp.getLast());
        this.title.set(emp.getTitle());
        this.id = emp.getId();
        this.phone.set(emp.getPhone());
        this.address.set(emp.getAddress());
        this.salary.set(emp.getSalary()); 
        
    }

    // Setter Methods for Employee data fields
   /*Setter method for employee first name
    @param firstName This is the parameter of the setFirstName method
    @return void No return value
    */
    public void setFirstName(String first) {
        this.firstname.set(first);
    }

    /*Setter method for employee last name
    @param lastName This is the parameter to the setLastName method
    @return void No return value
    */
    public void setLastName(String last) {
        this.lastname.set(last); 
    }

    /*Setter method for the employee phone number
    @param phone This is the parameter to the setPhone method
    @return void No return value
    */
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    /*Setter method for the employee address
    @param address This is the parameter to the setAddress method
    @return void No return value
    */
    public void setAddress(String address) {
        this.address.set(address);
    }


    /*Setter method for the employee title
    @param title This is the parameter for the setTitle method
    @return void No return value
    */
    public void setTitle(String title) {
        this.title.set(title);
    }

    /*Setter method for the employee salary
    @param salary This is the parameter for the salary method
    @return void No return value
    */
    public void setSalary(String salary) {
        this.salary.set(salary);
    }


    // Getter Methods for Employee data fields
   /* Getter method for the first name of employee
   @param args Unused
   @return string value of firstName
   */
    public String getFirstname() {
        return firstname.get();
    }

    /* Getter method for the last name of employee
   @param args Unused
   @return String value of lastName
   */
    public String getLast() {
        return lastname.get();
    }

    /* Getter method for the employee id
    @param args Unused
    @return int value of the id
    */
    public final long getId() {
        return id;
    }

    /*Getter method for the employee phone number
    @param args Unused
    @return String value of phoneNumber
    */
    public String getPhone() {
        return phone.get();
    }

    /*Getter method for the employee address
    @param args Unused
    @return String value of the address
    */
    public String getAddress() {
        return address.get();
    }

    /*Getter method for the employee title
    @param args Unused
    @return String value of the title
    */
    public String getTitle() {
        return title.get();
    }

    /*Getter method for the employee salary
    @param args Unused
    @return int value of the salary
    */
    public String getSalary() {
        return salary.get();
    }
    
    public StringProperty firstNameProperty(){
        return firstname;
    }
    
    public StringProperty lastNameProperty(){
        return lastname;
    }
    
    public StringProperty phoneProperty(){
        return phone;
    }
    
    public StringProperty addressProperty(){
        return address;
    }
    
    public StringProperty titleProperty(){
        return title;
    }
    public StringProperty salaryProperty(){
        return salary;
    }

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
                '}';
    }

    /* the equals() method is checking whether two employees have the same
        employee id as this is the only thing that is unique to each employee
        @param args an object which will be type casted to an Employee type
        @return boolean true or false
        */
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        final Employee other = (Employee) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.getFirstname(), other.getFirstname())
                && Objects.equals(this.getLast(), other.getLast())
                && Objects.equals(this.getTitle(), other.getTitle())
                && Objects.equals(this.getPhone(), other.getPhone())
                && Objects.equals(this.getSalary(), other.getSalary())
                && Objects.equals(this.getAddress(), other.getAddress());
    }

    /**
     * @param e is an Employee object
     */
    // This is a class method that will print the information for an employee object
    public static void printEmployee(Employee e) {
        System.out.print(e.firstname + " " + e.lastname + "\t" + e.phone + "\t" + "$" + e.salary + "\t" + e.address
                + "\t" + e.id + "\t" + e.title + "\n");
    }

}
