package com.ali.cyangatespringcase.service;

import com.ali.cyangatespringcase.model.Employee;
import com.ali.cyangatespringcase.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<Employee> createRecords(String spreadsheetBytes) throws IOException {

        byte[] decodedBytes = Base64.getDecoder().decode(spreadsheetBytes);
        List<Employee> employees = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(decodedBytes))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) { // Start from the second row (index 1)
                Row row = sheet.getRow(i);
                Employee employee = new Employee();
                employee.setName(row.getCell(0).getStringCellValue());
                employee.setSurname(row.getCell(1).getStringCellValue());
                employee.setAge((int) row.getCell(2).getNumericCellValue());
                employee.setSalary((int) row.getCell(3).getNumericCellValue());
                employee.setWorkYears((int) row.getCell(4).getNumericCellValue());
                employee.setTitle(row.getCell(5).getStringCellValue());
                employees.add(employee);
                System.out.println(employee);
            }
        } catch (IOException e) {
            throw new IOException("Error parsing spreadsheet data", e);
        }

        return employeeRepository.saveAll(employees);
    }

    public void updateRecord(Employee updatedEmployee) {
        employeeRepository.save(updatedEmployee);
    }

    public void deleteRecord(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee readRecord(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}


