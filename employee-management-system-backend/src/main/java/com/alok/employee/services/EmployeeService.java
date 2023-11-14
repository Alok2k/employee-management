package com.alok.employee.services;

import com.alok.employee.entity.EmployeeEntity;
import com.alok.employee.model.Employee;

import java.util.List;

public interface EmployeeService {


    EmployeeEntity createEmployee(EmployeeEntity employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);

    EmployeeEntity getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);
}
