package com.devsecops.ensa.employee.controller;

import com.devsecops.ensa.employee.dto.ApiResponse;
import com.devsecops.ensa.employee.entity.Employee;
import com.devsecops.ensa.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    
    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, @RequestHeader(name = "Accept-Language", defaultValue = "en") String lang) {
        try {
            Employee saved = service.saveEmployee(employee);
            String message = messageSource.getMessage("employee.added", null, new Locale(lang));
            return ResponseEntity.ok(new ApiResponse(message, true));
        } catch (Exception e) {
            String message = messageSource.getMessage("email.duplicate", null, new Locale(lang));
            return ResponseEntity.badRequest().body(new ApiResponse(message, false));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id, @RequestHeader(name = "Accept-Language", defaultValue = "en") String lang) {
        Employee employee = service.getEmployeeById(id);
        if (employee == null) {
            String message = messageSource.getMessage("employee.notfound", null, new Locale(lang));
            return ResponseEntity.badRequest().body(new ApiResponse(message, false));
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id, @RequestHeader(name = "Accept-Language", defaultValue = "en") String lang) {
        try {
            service.deleteEmployee(id);
            String message = messageSource.getMessage("employee.deleted", null, new Locale(lang));
            return ResponseEntity.ok(new ApiResponse(message, true));
        } catch (Exception e) {
            String message = messageSource.getMessage("employee.notfound", null, new Locale(lang));
            return ResponseEntity.badRequest().body(new ApiResponse(message, false));
        }
    }
}
