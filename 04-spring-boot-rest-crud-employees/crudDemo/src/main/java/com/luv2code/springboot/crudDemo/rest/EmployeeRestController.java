package com.luv2code.springboot.crudDemo.rest;
import com.luv2code.springboot.crudDemo.entity.Employee;
import com.luv2code.springboot.crudDemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController{

    private EmployeeService employeeService;
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee=employeeService.findById(employeeId);
        if(employee==null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        return employee;
    }

    //add mapping for POST /employees - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee=employeeService.save(theEmployee);
        return dbEmployee; //It has updated id from the database in the case of insert
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee=employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
         Employee tempEmployee=employeeService.findById(employeeId);
         if(tempEmployee==null){
             throw new RuntimeException("Employee id not found - "+employeeId);
         }
         employeeService.deleteById(employeeId);
         return "Deleted employee id - "+employeeId;
    }

}
