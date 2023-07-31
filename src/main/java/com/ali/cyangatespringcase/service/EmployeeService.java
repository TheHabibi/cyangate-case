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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<Employee> employees;
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(decodedBytes))) {
            Sheet sheet = workbook.getSheetAt(0);

            employees = IntStream.range(1, sheet.getLastRowNum() + 1)
                    .mapToObj(sheet::getRow)
                    .map(row -> {
                        Employee employee = new Employee();
                        employee.setName(row.getCell(0).getStringCellValue());
                        employee.setSurname(row.getCell(1).getStringCellValue());
                        employee.setAge((int) row.getCell(2).getNumericCellValue());
                        employee.setSalary((int) row.getCell(3).getNumericCellValue());
                        employee.setWorkYears((int) row.getCell(4).getNumericCellValue());
                        employee.setTitle(row.getCell(5).getStringCellValue());
                        System.out.println(employee);
                        return employee;
                    })
                    .collect(Collectors.toList());
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


