package com.dev.service;



import com.dev.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();

        employee1 = new Employee();
        employee1.setEmployeeId(1);

        employee2 = new Employee();
        employee2.setEmployeeId(2);
    }

    @Test
    void testCreateEmployee() {
        Employee result = employeeService.createEmployee(employee1);

        assertNotNull(result);
        assertEquals(1, result.getEmployeeId());
        assertEquals(1, employeeService.findAll().size());
    }

    @Test
    void testDeleteEmployee() {
        employeeService.createEmployee(employee1);
        employeeService.createEmployee(employee2);

        employeeService.deleteEmployee(1);

        List<Employee> employees = employeeService.findAll();

        assertEquals(1, employees.size());
        assertEquals(2, employees.get(0).getEmployeeId());
    }

    @Test
    void testDeleteEmployeeNotFound() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> employeeService.deleteEmployee(100)
        );

        assertEquals(
                "Employee not found with ID: 100",
                exception.getMessage()
        );
    }

    @Test
    void testFindById() {
        employeeService.createEmployee(employee1);
        employeeService.createEmployee(employee2);

        Employee result = employeeService.findById(2);

        assertNotNull(result);
        assertEquals(2, result.getEmployeeId());
    }

    @Test
    void testFindByIdNotFound() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> employeeService.findById(100)
        );

        assertEquals(
                "Employee not found with ID: 100",
                exception.getMessage()
        );
    }

    @Test
    void testFindAll() {
        employeeService.createEmployee(employee1);
        employeeService.createEmployee(employee2);

        List<Employee> employees = employeeService.findAll();

        assertEquals(2, employees.size());
        assertEquals(1, employees.get(0).getEmployeeId());
        assertEquals(2, employees.get(1).getEmployeeId());
    }

    @Test
    void testFindAllWhenNoEmployeesExist() {
        List<Employee> employees = employeeService.findAll();

        assertNotNull(employees);
        assertTrue(employees.isEmpty());
    }

    @Test
    void testFindAllReturnsSeparateList() {
        employeeService.createEmployee(employee1);

        List<Employee> employees = employeeService.findAll();
        employees.clear();

        assertEquals(1, employeeService.findAll().size());
    }
}