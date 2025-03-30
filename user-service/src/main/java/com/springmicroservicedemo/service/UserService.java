package com.springmicroservicedemo.service;

import com.springmicroservicedemo.client.DepartmentClient;
import com.springmicroservicedemo.entity.Users;
import com.springmicroservicedemo.repository.UserRepository;
import com.springmicroservicedemo.vo.Department;
import com.springmicroservicedemo.vo.ResponseTemplateVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public static final String DEPT_SERVICE = "myCircuitBreaker";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentClient departmentClient;

    // Need to define this value in properties file
   // private static final String BASE_URL = "http://DEPARTMENT-SERVICE/departments/";
    public Users saveUser(Users users){
        log.info("Inside saveUser of UserService");
        return  userRepository.save(users);
    }

    @CircuitBreaker(name = DEPT_SERVICE , fallbackMethod = "departmentFallBackMethod")
    public ResponseTemplateVO getUserIdWithDepartment(Long id) {
        log.info("Inside getUserIdWithDepartment of UserService");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Users user = userRepository.findByUserId(id);
        //Calling Department service through webclient
        Department department = departmentClient.findByDepartmentId(user.getUserId());
        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);
        return responseTemplateVO;
    }

    //fallback method
    public ResponseTemplateVO departmentFallBackMethod(Exception ex) {
        return this.getDefaultDepartmentDetails();
    }

    public ResponseTemplateVO getDefaultDepartmentDetails() {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentAddress("TEST Address");
        department.setDepartmentCode("01");
        department.setDepartmentName("TEST Department");
        responseTemplateVO.setDepartment(department);
        return responseTemplateVO;
    }
}
