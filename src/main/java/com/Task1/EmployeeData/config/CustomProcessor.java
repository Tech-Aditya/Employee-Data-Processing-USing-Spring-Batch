package com.Task1.EmployeeData.config;

import com.Task1.EmployeeData.model.Employee;
import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee item) throws Exception {
        return item;

    }
}
