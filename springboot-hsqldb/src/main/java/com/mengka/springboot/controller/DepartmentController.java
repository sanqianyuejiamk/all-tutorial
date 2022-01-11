package com.mengka.springboot.controller;

import com.mengka.springboot.model.Department;
import com.mengka.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dept")
    public ResponseEntity<List<Department>> listDepartments(){
        return new ResponseEntity<>(departmentService.allDepartments(), HttpStatus.OK);
    }

    @GetMapping("/dept/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") int id){
        return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
    }
}
