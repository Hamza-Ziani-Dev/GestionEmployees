package com.springboot.backend.GestionEmployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springboot.backend.GestionEmployees.models.Employee;


@CrossOrigin("http://localhost:4200")
@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {


}
