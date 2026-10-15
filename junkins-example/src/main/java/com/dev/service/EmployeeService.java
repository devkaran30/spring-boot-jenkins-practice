package com.dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.model.Employee;

@Service 
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>(); 
        
    public Employee createEmployee(Employee employee){
        employees.add(employee);
        return employee;

    }

    public void deleteEmployee(int employeeId){
        boolean removed = employees.removeIf(
                employee -> employee.getEmployeeId() == employeeId
        );

        if (!removed) {
            throw new RuntimeException(
                    "Employee not found with ID: " + employeeId
            );
        }
    } 

    public Employee findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Employee not found with ID: " + id
                ));
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}