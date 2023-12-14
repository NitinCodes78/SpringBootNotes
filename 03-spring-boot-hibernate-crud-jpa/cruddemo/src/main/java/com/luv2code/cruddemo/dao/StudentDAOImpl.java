package com.luv2code.cruddemo.dao;
import java.util.List;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entity manager

    private EntityManager entityManager;

    //inject entity manager using construtor injection

    @Autowired
     public StudentDAOImpl(EntityManager entityManager){
         this.entityManager=entityManager;
     }

    //implement save method
    //    Transactional annotation since we are performing an update on the database, no need to add for the read operation since we are not making any update
    @Override
     @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    public Student findById(Integer id){

        return entityManager.find(Student.class,id);
    }


    public List<Student> findByFirstName(String theLastName){

        //JPQL Named parameters are prefixed with a colo: , think of this as placeholder that is filled in later, no hard coding that firstName is this
        TypedQuery<Student> theQuery=entityManager.createQuery("From Student where firstName = :theData", Student.class);

        theQuery.setParameter("theData", theLastName);


        return theQuery.getResultList();

    }

    @Override
    @Transactional
    public void updateIt(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve the student

        Student theStudent=entityManager.find(Student.class, id);
        entityManager.remove(theStudent);

        //delete the student
    }
    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }


    public List<Student> findAll(){
        //create query

        //In From Student, it's the name of the JPA entity the class name, and other fields are also entity fields and not table
        TypedQuery<Student>
                theQuery=entityManager.createQuery("From Student order by lastName", Student.class);
        //Here lastName is the field of JPA entity
        //return query results

        return theQuery.getResultList();
    }


}
