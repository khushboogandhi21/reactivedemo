package com.work.year22.reactivedemo.service.impl;

import com.work.year22.reactivedemo.dto.Employee;
import com.work.year22.reactivedemo.service.EmployeeService;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Mono<Employee> getEmployeeById(int id) {
        return Mono.empty();
    }

//    private Employee retEmp(){
//        return new Employee(4,"John", Arrays.asList("Mumbai","Bangalore"));
//    }
}
