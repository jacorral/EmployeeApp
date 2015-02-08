/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

/**
 *
 * @author angel
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.beans.Observable;
import java.util.List;
import java.util.ArrayList;


public class ListManager {
    List<Employee> list = new ArrayList<>();
    
    ObservableList<Employee> observableList = FXCollections.observableArrayList(list);
    
    public static ListManager instance = null;
    
    protected ListManager(){
        
    }
    public static ListManager getInstance(){
        if (instance == null){
            instance = new ListManager();
        }
        return instance;
    }
    
    public void addListener(
                ListChangeListener<? super Employee> ll){
        observableList.addListener(ll);
    }
    
    public void removeListener(
                ListChangeListener<? super Employee> ll){
        observableList.removeListener(ll);
    }
    
    public void addEmployee(Employee emp){
        Employee employee = new Employee(emp);
        observableList.add(employee);
    }
    
    public void deleteEmployee(Employee emp){
        observableList.remove(emp);
    }
   
    public void updateEmployee(Employee emp){
        observableList.add(emp);
    }
    
    public ObservableList<Employee> getObservableEmployees(){
        return observableList;
    }
    
    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> copyList = new ArrayList<>();
        observableList.forEach((p) -> 
        copyList.add(p));
        return copyList;
    }
    
}
