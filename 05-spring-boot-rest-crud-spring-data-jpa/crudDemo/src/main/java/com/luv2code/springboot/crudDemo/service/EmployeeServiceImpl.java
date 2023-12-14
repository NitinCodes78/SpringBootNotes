package com.luv2code.springboot.crudDemo.service;
import com.luv2code.springboot.crudDemo.dao.EmployeeRepository;
import com.luv2code.springboot.crudDemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        //We used optional as it automatically checks for null, JPA repository makes use of it otherwise it was giving error, can click on employeeRepo interface and check the type it expects for various pre-defined methods
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee=null;
        if(result.isPresent()){
            theEmployee=result.get();
        }else{
            throw new RuntimeException(("Did not find employee id- "+theId));
        }
        return theEmployee;
    }

    //Removed @Transactional since JpaRepository provides this functinality
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
//    @Transactional
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);}
}
