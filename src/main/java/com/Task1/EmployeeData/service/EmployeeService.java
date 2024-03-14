package com.Task1.EmployeeData.service;

import com.Task1.EmployeeData.model.Employee;
import com.Task1.EmployeeData.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

//    @Scheduled(fixedDelay = 5000)
//    public void salaryHigh() {
//        List<Employee> employees = employeeRepository.topSalary();
//        employees.forEach(System.out::println);
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void salaryLow() {
//        List<Employee> employees = employeeRepository.lowSalary();
//        employees.forEach(System.out::println);
//    }

    @Scheduled(fixedDelay = 4000)
    public void getEmp(){
        List<Employee> employees = employeeRepository.findTop5ByOrderBySalaryDesc();
        employees.forEach(System.out::println);
    }
}
