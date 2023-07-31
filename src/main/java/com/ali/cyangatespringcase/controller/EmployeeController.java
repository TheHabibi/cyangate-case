package com.ali.cyangatespringcase.controller;

import com.ali.cyangatespringcase.model.Employee;
import com.ali.cyangatespringcase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/createRecord")
    public ResponseEntity<List<Employee>> createRecord(@RequestBody String spreadsheetBytes) throws IOException {
        System.out.println(spreadsheetBytes);
        List<Employee> insertedRecords = employeeService.createRecords(spreadsheetBytes);
        return new ResponseEntity<>(insertedRecords, HttpStatus.OK);
    }

    @PutMapping("/updateRecord")
    public ResponseEntity<String> updateRecord(@RequestBody Employee updatedEmployee) {
        employeeService.updateRecord(updatedEmployee);
        String message = "Record updated successfully!";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/deleteRecord")
    public ResponseEntity<String> deleteRecord(@RequestParam Long id) {
        employeeService.deleteRecord(id);
        String message = "Record deleted successfully!";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/readRecord")
    public ResponseEntity<Employee> readRecord(@RequestParam Long id) {
        Employee employee = employeeService.readRecord(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

