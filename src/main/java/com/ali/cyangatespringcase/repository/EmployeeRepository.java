package com.ali.cyangatespringcase.repository;

import com.ali.cyangatespringcase.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

