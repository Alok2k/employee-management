package com.alok.employee.services;

import com.alok.employee.customexception.EmployeeNotFoundException;
import com.alok.employee.customexception.EmptyInputException;
import com.alok.employee.customexception.EmptyListException;
import com.alok.employee.model.Employee;
import com.alok.employee.entity.EmployeeEntity;
import com.alok.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        if (employee.getFirstName().isEmpty()||employee.getEmailId().isEmpty()){
            throw new EmptyInputException("666","input fields are null");
        }
        EmployeeEntity savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        try {
            if (employeeEntities.isEmpty()){
                throw new EmptyListException();
            }
            else {
                List<Employee> employees = employeeEntities
                        .stream()
                        .map(emp -> new Employee(
                                emp.getId(),
                                emp.getFirstName(),
                                emp.getLastName(),
                                emp.getEmailId()))
                        .collect(Collectors.toList());
                return employees;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmployee(Long id) throws EmptyInputException {
        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
            return true;
        } else {
            throw new EmptyInputException("555", "Employee not found for deletion");
        }
    }


    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        if (employeeRepository.findById(id).isEmpty()){
            throw new NoSuchElementException();
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        return employeeEntity;

    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        try{
            Optional<EmployeeEntity> optionalEmployeeEntity=employeeRepository.findById(id);
            if(optionalEmployeeEntity.isPresent()){
                EmployeeEntity employeeEntity=optionalEmployeeEntity.get();
                employeeEntity.setEmailId(employee.getEmailId());
                employeeEntity.setFirstName(employee.getFirstName());
                employeeEntity.setLastName(employee.getLastName());
                employeeRepository.save(employeeEntity);
                return employee;
            }
            else {
                throw new EmployeeNotFoundException();
            }


        } catch (Exception e) {
            throw new RuntimeException("Error updating employee",e);
        }
    }
}
