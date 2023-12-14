package com.luv2code.springboot.crudDemo.dao;

import com.luv2code.springboot.crudDemo.entity.Employee;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


//Previously we used to define the interface for say basic employee details and then implement them, similarly for other things such as payroll or any other thing. That's quite lengthy approach that invloved code repitition, jpa repository is an alternative to it.

//No need to define api endpoints for basic crud because we added spring-data-rest dependency for that

//By default, the path is the pluralised form of the entity name i.e. first alphabat in the lower case and add s at the last
@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //here first argument of jpaRepository is the entity name and second arguement is the datatype of primary key
    //that's it ... no need to write any code LOL! we get various crud methods for free such as findAll(), findById(), save(), deleteById() ....
}
