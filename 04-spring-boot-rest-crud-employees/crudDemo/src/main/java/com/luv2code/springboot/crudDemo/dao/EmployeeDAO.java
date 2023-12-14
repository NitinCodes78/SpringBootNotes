package com.luv2code.springboot.crudDemo.dao;
import java.util.List;
import com.luv2code.springboot.crudDemo.entity.Employee;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void deleteById(int theId);
}
