package com.springmicroservicesdemo.controller;

import com.springmicroservicesdemo.entity.Department;
import com.springmicroservicesdemo.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartments(@RequestBody Department department) {
        log.info("Inside saveDepartments of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findByDepartmentId(@PathVariable("id") Long departmentId) {
        log.info("Inside findByDepartmentId of DepartmentController");
        return departmentService.findByDepartmentId(departmentId);

    }
}
