package com.springmicroservicesdemo.services;

import com.springmicroservicesdemo.entity.Department;
import com.springmicroservicesdemo.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findByDepartmentId(Long departmentId) {
        log.info("Inside findByDepartmentId of DepartmentService");
        return departmentRepository.findByDepartmentId(departmentId);
    }
}
