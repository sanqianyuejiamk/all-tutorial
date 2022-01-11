package com.mengka.springboot.service;

import com.mengka.springboot.model.Department;
import com.mengka.springboot.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> allDepartments(){
        return departmentRepo.findAll();
    }

    public Department getDepartment(int id){
        return departmentRepo.findById(id).get();
    }
}
