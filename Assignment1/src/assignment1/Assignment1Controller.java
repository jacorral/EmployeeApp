/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
//

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.scene.input.ContextMenuEvent;

/**
 *
 * @author angel
 */
public class Assignment1Controller implements Initializable {
    
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
    private TreeView<Employee> employeeTreeView;
    @FXML
    private Button updateButton;
    //Create logger and employee tree manager objects
    private static final Logger logger = Logger.getLogger(
                    Assignment1Controller.class.getName());
    private final EmployeeManager em = EmployeeManager.getInstance();
    private Employee theEmployee = null;
    private boolean changeOK = false;
    private BooleanProperty enableUpdateProperty;
    @FXML
    private TextField idTextField;
    
    @FXML
    private Button clearButton;
    private BooleanProperty enableClearProperty;
    
    @FXML
    private Button deleteButton;
    private BooleanProperty enableDeleteProperty;
    @FXML
    private Button addButton;
    private BooleanProperty enableAddProperty;
    
    

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logger.setLevel(Level.FINE);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);
        
        try{
            FileHandler fileHandler = new FileHandler();
            //records sent to file javaN.log in user's home directory
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
            logger.log(Level.FINE, "Created File Handler");
            
            }catch (IOException | SecurityException ex){
                logger.log(Level.SEVERE, "Couldn't create FileHandler", ex);
            }
        
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
        TreeItem<Employee> rootNode = new TreeItem<>(
                new Employee("John", "Doe"));
        buildTreeView(rootNode);
        employeeTreeView.setRoot(rootNode);
        employeeTreeView.getRoot().setExpanded(true);
        employeeTreeView.getSelectionModel().selectedItemProperty()
                .addListener(treeSelectionListener);
      
        
    }    
    
    private void buildData(){
        em.addEmployee(new Employee("Bill", "Clinton", "Employee"));
        em.addEmployee(new Secretary("Jill","Yang", "123BIC"));
        em.addEmployee(new Supervisor());
        em.addEmployee(new Employee("Jane", "Doe", "Secretary"));
        em.addEmployee(new Employee("Michael", "Jordan", "Supervisor"));
        em.addEmployee(new Employee());
        logger.log(Level.FINE, em.getAllEmployees().toString());
    }
    private void buildTreeView(TreeItem<Employee> root){
        
        em.addListener(employeeTreeListener);
        
        em.getAllEmployees().stream().forEach((p)->{
            root.getChildren().add(new TreeItem<>(p));
        });
    }
    
    private final ChangeListener<TreeItem<Employee>> treeSelectionListener =
            (ov, oldValue, newValue) -> {
                TreeItem<Employee> treeItem = newValue;
                logger.log(Level.FINE, "selected item = {0}", treeItem);
                enableUpdateProperty.set(false);
                changeOK = false;
                if (treeItem == null || treeItem.equals(employeeTreeView.getRoot())){
                    clearForm();
                    return;
                }
                theEmployee = new Employee(treeItem.getValue());
                logger.log(Level.FINE, "selected employee = {0}", theEmployee);
                configureEditPanelBindings(theEmployee);
                
                changeOK = true;
            };
    
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

    @FXML
    private void handleKeyAction(KeyEvent event) {
        if(changeOK){
            enableUpdateProperty.set(true);
            enableClearProperty.set(true);
            enableDeleteProperty.set(true);
            enableAddProperty.set(true);
        }
    }
    
    private final MapChangeListener<Long, Employee> employeeTreeListener = 
            change -> {
                if (Platform.isFxApplicationThread()){
                    logger.log(Level.FINE, "Is JavaFX Application Thread");
                    updateTree(change);
                }else{
                    logger.log(Level.FINE, "Is BACKGROUND Thread");
                    Platform.runLater(() -> updateTree(change));
                }
            };
    
    private void updateTree(MapChangeListener.Change<? extends Long, ? extends Employee> change){
        if (change.getValueAdded() != null){
            for (TreeItem<Employee> node : employeeTreeView.getRoot().getChildren() ){
                if (change.getKey().equals(node.getValue().getId())){
                    node.setValue(change.getValueAdded());
                    return;
                }
            }
        }
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {
        System.out.println("Update button pressed");
        enableUpdateProperty.set(false);
        em.updateEmployee(theEmployee);
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        System.out.println("Clear button pressed");
        enableClearProperty.set(false);
        clearForm();
        
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        idTextField.clear();
        String first = firstnameTextField.getText();
        String last = lastnameTextField.getText();
        Employee e = new Employee(first,last);
        System.out.println("Add button pressed");
        enableClearProperty.set(false);
        em.addEmployee(e);
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        System.out.println("Delete button pressed");
        enableDeleteProperty.set(false);
        em.deleteEmployee(theEmployee);
        
    }
    
}
