package com.springmicroservicedemo.client;


import com.springmicroservicedemo.vo.Department;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface DepartmentClient {

    @GetExchange("/{id}")
    Department findByDepartmentId(@PathVariable("id") Long departmentId);
}
