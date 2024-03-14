package com.Task1.EmployeeData.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private double salary;

}
