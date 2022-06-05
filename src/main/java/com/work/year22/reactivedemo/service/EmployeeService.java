package com.work.year22.reactivedemo.service;

import com.work.year22.reactivedemo.dto.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    public Mono<Employee> getEmployeeById(int  id);
}
