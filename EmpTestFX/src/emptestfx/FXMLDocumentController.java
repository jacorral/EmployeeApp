/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emptestfx;

import com.daBandit.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author angel
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private Button updateButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private final Employee theEmp = null;

    private final ListManager em = ListManager.getInstance();

    //private TableView<Employee> employees = new TableView<>();
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    //will keep track of changes
    private boolean changeOK = false;

    //Properties used for the buttons
    private BooleanProperty enableUpdateProperty;
    private BooleanProperty enableClearProperty;
    private BooleanProperty enableDeleteProperty;
    private BooleanProperty enableAddProperty;
    @FXML
    private TableColumn<?, ?> idTableColumn;
    @FXML
    private TableColumn<?, ?> firstnameTableColumn;
    @FXML
    private TableColumn<?, ?> titleTableColumn;
    @FXML
    private TableColumn<?, ?> phoneTableColumn;
    @FXML
    private TableView<Employee> employees;
    @FXML
    private TableColumn<?, ?> lastnameTableColumn;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        enableUpdateProperty = new SimpleBooleanProperty(
                this, "enableUpdate", false);
        updateButton.disableProperty().bind(enableUpdateProperty.not());

        enableClearProperty = new SimpleBooleanProperty(
                this, "enableClear", false);
        clearButton.disableProperty().bind(enableClearProperty.not());

        enableDeleteProperty = new SimpleBooleanProperty(
                this, "enableDelete", false);
        deleteButton.disableProperty().bind(enableDeleteProperty.not());

        enableAddProperty = new SimpleBooleanProperty(
                this, "enableAdd", true);
        addButton.disableProperty().bind(enableAddProperty.not());

        buildData();
        //read();

        buildTable();

    }
    // private final ChangeListener

    @FXML
    private void handleKeyAction(KeyEvent event) {
        if (changeOK) {
            enableUpdateProperty.set(true);
            enableAddProperty.set(true);
            enableClearProperty.set(false);
            enableDeleteProperty.set(false);
        }
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {

        enableUpdateProperty.set(false);
        //em.updateEmployee(theEmp);
        search();

    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        enableClearProperty.set(false);
        enableDeleteProperty.set(false);
        clearForm();

    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        enableAddProperty.set(false);
        Employee newEmp = new Employee(firstnameTextField.getText(), lastnameTextField.getText());
        em.addEmployee(newEmp);
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        //enableDeleteProperty.set(false);

        // System.out.println(theEmp.getFirstname());
        System.out.println(employees.getSelectionModel().getSelectedItem().getFirstname());
        Employee thisEmp = employees.getSelectionModel().getSelectedItem();
        em.deleteEmployee(thisEmp);
        clearForm();
        enableDeleteProperty.set(false);
        enableClearProperty.set(false);
    }

    private void buildData() {
        em.addEmployee(new Employee("Bill", "Clinton", "Employee"));
        Secretary jane = new Secretary("Jill", "Yang", "123BIC");
        em.addEmployee(jane);
        em.addEmployee(new Supervisor("Bugs", "Bunny", "Engineering", "100", jane));
        em.addEmployee(new Employee("Jane", "Doe", "Secretary"));
        em.addEmployee(new Employee("Michael", "Jordan", "Supervisor"));
        em.addEmployee(new Employee());
    }

    private void configureEditPanelBindings(Employee e) {
        firstnameTextField.textProperty()
                .bindBidirectional(e.firstNameProperty());
        lastnameTextField.textProperty()
                .bindBidirectional(e.lastNameProperty());
        titleTextField.textProperty()
                .bindBidirectional(e.titleProperty());
        phoneTextField.textProperty()
                .bindBidirectional(e.phoneProperty());
        salaryTextField.textProperty()
                .bindBidirectional(e.salaryProperty());
        addressTextField.textProperty()
                .bindBidirectional(e.addressProperty());
        idTextField.setText(Long.toString(e.getId()));

    }

    private void clearForm() {
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        titleTextField.setText("");
        phoneTextField.setText("");
        salaryTextField.setText("");
        addressTextField.setText("");
        idTextField.setText("");
    }

    private void buildTable() {

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
       
        lastnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("last"));
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        em.addListener(employeeListListener);

        employeeList = FXCollections.observableList(em.getAllEmployees());
       //System.out.println(employeeList);

        // employeesTable.getItems().addAll(employeeList);
        employees.setItems(employeeList);

        //Listener for table selection changes
        employees.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            enableUpdateProperty.set(false);
            enableClearProperty.set(true);
            enableDeleteProperty.set(true);
            changeOK = false;

            if (employees.getSelectionModel().getSelectedItem() != null) {

                System.out.println(employees.getSelectionModel().getSelectedItem().getFirstname());

                Employee theEmp = new Employee(newValue);
                configureEditPanelBindings(theEmp);

            }
            changeOK = true;
        });

    }
    //Observer that keeps track of employees getting removed, changed and added to the list

    private final ListChangeListener<Employee> employeeListListener
            = (onChange) -> {
                while (onChange.next()) {
                    System.out.println("The list was changed....I think!");
                    if (onChange.wasReplaced()) {
                        System.out.println("A change was replaced");

                    } else if (onChange.wasRemoved()) {

                        int rindl = onChange.getFrom();
                        employeeList.remove(rindl);
                        System.out.println("An employee was removed at index: " + rindl);

                    } else if (onChange.wasAdded()) {

                        int aindl = onChange.getFrom();
                        int aindh = onChange.getTo();
                        //employeeList.addAll(0, onChange.getAddedSubList());

                        employeeList.addAll(onChange.getAddedSubList());

                        System.out.println("An employee was added at index: " + aindl);

                    }
                }

            };

    public void save() {
        ArrayList<Employee> list = new ArrayList(em.getAllEmployees());

        File file = new File("data.dat");

        try (FileOutputStream fop = new FileOutputStream(file)) {
            //create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            } else {
                // Code for saving data

                ObjectOutputStream oos = new ObjectOutputStream(fop);
                
                oos.writeInt(list.size());
                
                for (int i = 0; i < list.size(); i++){
                    oos.writeObject(list.get(i));
                }
               
                // System.out.println("Employee being serialized: " + list.get(i).getFirstname());

                oos.close();
                fop.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void read() {
        ArrayList<Employee> inList = new ArrayList<>();

        try {
            FileInputStream inputFileStream = new FileInputStream("data.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
            //System.out.println("Deserial data: " + objectInputStream.readObject().getClass());
            int listSize = objectInputStream.readInt();
            
            for (int i=0; i < listSize; i++){
                //em.addEmployee((Employee)objectInputStream.readObject());
                inList.add((Employee) objectInputStream.readObject());
                
                
            }
            
            //System.out.println("Employee being de=serialized: " + desEmp.getFirstname());
            for (int i = 0; i < inList.size(); i++){
                //em.addEmployee(inList.get(i));
               // System.out.println(inList.get(i).getFirstname());
                System.out.println("index #: " + i + " "+ (inList.get(i) instanceof Employee));
            }
            //em.addEmployee(inList.get(3));

            objectInputStream.close();
            inputFileStream.close();

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
        //System.out.println("Employee being de=serialized: " + desEmp.getFirstname());
    }
    
    public void search(){
        
        String searchString = firstnameTextField.getText();
        clearForm();
        System.out.println("searching for: " + searchString);
        ArrayList<Employee> list = em.getAllEmployees();
        System.out.println("name: " + list.get(0).firstname.get());
        for (int i = 0; i < list.size(); i++){
            if(searchString.equalsIgnoreCase( list.get(i).firstname.get())){
                
                configureEditPanelBindings(list.get(i));
                
                System.out.println("search: "+ searchString +"  found: " + list.get(i).getFirstname());
            }
            
        }
    }
    

}
