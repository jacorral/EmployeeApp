/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emptestfx;

import com.daBandit.*;
import java.net.URL;
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
import javafx.scene.input.InputMethodEvent;
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
    
    private Employee theEmp = null;
    
    private final ListManager em = ListManager.getInstance();
    
    //private TableView<Employee> employees = new TableView<>();
    private  ObservableList<Employee> employeeList = FXCollections.observableArrayList();
   
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
        
        buildTable();
       
    }    
   // private final ChangeListener
    

    @FXML
    private void handleKeyAction(KeyEvent event) {
        if (changeOK){
            enableUpdateProperty.set(true);
            enableAddProperty.set(true);
            enableClearProperty.set(false);
            enableDeleteProperty.set(false);
        }
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {
      
            enableUpdateProperty.set(false);
            em.updateEmployee(theEmp);
        
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        enableClearProperty.set(false);
        clearForm();
        
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        enableAddProperty.set(false);
        em.addEmployee(theEmp);
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        enableDeleteProperty.set(false);
        em.deleteEmployee(theEmp);
    }
    
    
    private void buildData(){
        em.addEmployee(new Employee("Bill", "Clinton", "Employee"));
        em.addEmployee(new Secretary("Jill","Yang", "123BIC"));
        em.addEmployee(new Supervisor());
        em.addEmployee(new Employee("Jane", "Doe", "Secretary"));
        em.addEmployee(new Employee("Michael", "Jordan", "Supervisor"));
        em.addEmployee(new Employee());
    }
    
    
    
    private void configureEditPanelBindings(Employee e){
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
    
    
    
    
    private void clearForm(){
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        titleTextField.setText("");
        phoneTextField.setText("");
        salaryTextField.setText("");
        addressTextField.setText("");
        idTextField.setText("");
    }
    
    private void buildTable(){
        
          
       idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
       firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
       lastnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
       titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
       phoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
       
       em.addListener(employeeListListener);
       employeeList = FXCollections.observableList(em.getAllEmployees());
       //System.out.println(employeeList);
       
       
      // employeesTable.getItems().addAll(employeeList);
       employees.setItems(employeeList);
       
       
       //Listener for table selection changes
       
       employees.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) ->{
        enableUpdateProperty.set(false);
        enableClearProperty.set(true);
        enableDeleteProperty.set(true);
        changeOK = false;
        
        if (employees.getSelectionModel().getSelectedItem() != null){
            
            System.out.println( employees.getSelectionModel().getSelectedItem().getFirstname());
            
            Employee theEmp = new Employee(newValue);
            configureEditPanelBindings(theEmp);
            
        }
        changeOK = true;
    });
        
    }
  
    private final ListChangeListener<Employee> employeeListListener = 
            (change) -> {
                //if (change.wasAdded())
                   
                
            };


}
