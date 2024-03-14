package com.Task1.EmployeeData.repository;

import com.Task1.EmployeeData.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


//    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT 5")
//    List<Employee> topSalary();

    @Query(value = "SELECT * FROM Employee ORDER BY salary DESC LIMIT 5", nativeQuery = true)
    List<Employee> topSalary();
    

    @Query(value = "SELECT * FROM Employee ORDER BY salary ASC LIMIT 5", nativeQuery = true)
    List<Employee> lowSalary();

    List<Employee> findTop5ByOrderBySalaryDesc();
}
