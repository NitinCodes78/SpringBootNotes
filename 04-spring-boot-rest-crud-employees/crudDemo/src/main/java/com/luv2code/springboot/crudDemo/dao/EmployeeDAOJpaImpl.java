package com.luv2code.springboot.crudDemo.dao;
import java.util.List;
import com.luv2code.springboot.crudDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//We don't use @Transactional at DAO layer, it will be handled at service layer
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    //define field for entityManager
     private EntityManager entityManager;

    //setUp constructor Injection

    //Constructor Injection for EntityManager, automatically created by Spring Boot
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
         entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll(){

        //create a query
        TypedQuery<Employee> theQuery=entityManager.createQuery("from Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees=theQuery.getResultList();
        //return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        Employee theEmployee=entityManager.find(Employee.class,theId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        Employee dbEmployee=entityManager.merge(theEmployee);
        //If the id==0, it will insert it as a new employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        Employee theEmployee=entityManager.find(Employee.class, theId);
        entityManager.remove(theEmployee);
    }
}
