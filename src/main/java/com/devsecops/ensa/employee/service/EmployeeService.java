package com.devsecops.ensa.employee.service;

import com.devsecops.ensa.employee.entity.Employee;
import com.devsecops.ensa.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee) throws Exception {
        if (repository.findByEmail(employee.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        return repository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEmployee(Long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Employee not found");
        }
        repository.deleteById(id);
    }
}
