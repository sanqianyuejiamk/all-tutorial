package com.mengka.springboot.component;

import com.mengka.springboot.model.Department;
import com.mengka.springboot.model.Employee;
import com.mengka.springboot.repo.DepartmentRepo;
import com.mengka.springboot.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mengka
 * @Date 2022-01-11 16:19
 */
@Order(value = 3)
@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public void run(String... args) throws Exception {
        Employee employee = employeeRepo.save(new Employee(1, "Dhiraj", 20, 3456));
        Employee employee2 = employeeRepo.save(new Employee(2, "Mengka", 30, 3756));
        Employee employee3 = employeeRepo.save(new Employee(3, "Hyy", 31, 3856));
        Stream.of(
                new Department(1, "Computer Science", "Computer Science department", Stream.of(employee,employee2).collect(Collectors.toList())),
                new Department(2, "Mengka Science", "Mengka Science department",Stream.of(employee3).collect(Collectors.toList())))
                .forEach(department -> departmentRepo.save(department));



    }
}
