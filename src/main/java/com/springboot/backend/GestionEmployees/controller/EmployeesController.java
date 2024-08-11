package com.springboot.backend.GestionEmployees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.GestionEmployees.exception.NotFoundException;
import com.springboot.backend.GestionEmployees.models.Employee;
import com.springboot.backend.GestionEmployees.repository.EmployeesRepository;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:4200")
public class EmployeesController {
    // Injecting Employee Repository
    @Autowired
    private EmployeesRepository employeesRepository;

    // Get All Employees:
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeesRepository.findAll();
    }

    // Create Employee:
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeesRepository.save(employee);
    }


    // Get Signle Employee:
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee =  employeesRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Employee with id " + id + " not found")
        );
        return ResponseEntity.ok(employee);
    }

    // Update Employee:
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeesRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Employee with id " + id + " not found")
        );
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        Employee updatedEmployee = employeesRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee :
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeesRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Employee with id " + id + " not found")
        );
        employeesRepository.delete(employee);
        return ResponseEntity.ok("Employee with id " + id + " deleted");
    }
        
}
