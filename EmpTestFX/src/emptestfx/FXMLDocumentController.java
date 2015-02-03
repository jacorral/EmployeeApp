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
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
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
    @FXML
    private ListView<Employee> emListView;
    
    private final ListManager em = ListManager.getInstance();
   
    //will keep track of changes
    private boolean changeOK = false;
    
    //Properties used for the buttons
    private BooleanProperty enableUpdateProperty;
    private BooleanProperty enableClearProperty;
    private BooleanProperty enableDeleteProperty;
    private BooleanProperty enableAddProperty;
    
    
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
        buildListView();
       
        // emListView for the List View
        
    
        
        //emListView.getItems().addListener(employeeListListener);
    }    
   // private final ChangeListener
    

    @FXML
    private void handleKeyAction(KeyEvent event) {
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
    }
    
    
    private void buildData(){
        em.addEmployee(new Employee("Bill", "Clinton", "Employee"));
        em.addEmployee(new Secretary("Jill","Yang", "123BIC"));
        em.addEmployee(new Supervisor());
        em.addEmployee(new Employee("Jane", "Doe", "Secretary"));
        em.addEmployee(new Employee("Michael", "Jordan", "Supervisor"));
        em.addEmployee(new Employee());
    }
    
    private void buildListView(){
        em.addListener(listViewListener);
       
       
        emListView.setItems(em.getObservableEmployees());
    }
    
    private final ListChangeListener<Employee> listViewListener = 
        (change) -> {
            if(change.wasAdded(){
                for(Employee em : change.getAddedSubList()){
                   
                }
                
            })
                
                
            };
}
