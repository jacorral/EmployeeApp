/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableList;
import java.util.concurrent.ConcurrentHashMap;
import javafx.collections.MapChangeListener;
import javafx.beans.InvalidationListener;
import java.util.List;
import java.util.ArrayList;




/**
 *
 * @author angel
 */
public class EmployeeManager {
    
    
    
    public final ObservableMap<Long, Employee> observableMap =
            FXCollections.observableMap(new ConcurrentHashMap<Long, Employee>());
    
    private static EmployeeManager instance = null;
    
    
    
    
    protected EmployeeManager(){
        //Singleton class: Direct instantiation is not allowed
    }
    
    
    
    public  static EmployeeManager getInstance(){
        if (instance == null){
            instance = new EmployeeManager();
        }
        return instance;
    }
    
    public  void addListener(
                    MapChangeListener<? super Long, ? super Employee> ml){
        observableMap.addListener(ml);
    }
    
    public  void removeListener(
                    MapChangeListener<? super Long, ? super Employee> ml){
        observableMap.removeListener(ml);
    }
    
    public  void addListener(InvalidationListener il){
        observableMap.addListener(il);
    }
    
    public  void removeListener(InvalidationListener il){
        observableMap.removeListener(il);
    }
    
    public  void addEmployee(Employee emp){
        Employee employee = new Employee(emp);
        observableMap.put(employee.getId(), emp);
    }
    
    public void updateEmployee(Employee emp){
        addEmployee(emp);
    }
    
    public  void deleteEmployee(Employee emp){
        observableMap.remove(emp.getId());
    }
    
    public  List<Employee> getAllEmployees(){
        List<Employee> copyList = new ArrayList<>();
        observableMap.values().stream().forEach((emp) ->
                copyList.add(new Employee(emp)));
         //ObservableList<Employee> aList = FXCollections.observableArrayList(copyList);
        return copyList;
    }
    
    
   
}
