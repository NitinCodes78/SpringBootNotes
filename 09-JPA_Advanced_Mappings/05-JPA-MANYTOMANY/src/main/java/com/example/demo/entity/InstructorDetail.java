package com.example.demo.entity;
import jakarta.persistence.*;
import org.aspectj.weaver.IClassFileProvider;
import org.aspectj.weaver.IClassWeaver;
import org.hibernate.Hibernate;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

//    mapperBy tells Hibernate:-
//
//    1. Look at the instructorDetail property in the   Instructor class
//    2. Use information from the Instructor class @JoinColumn
//    3. To help find associated instrutor
//    (So basically, it will look for instructor_detail_id in the instructor table and determine the appropriate instructor from there)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    //add @OneToOne annotation here for bidirectional

    //Refers to "instructorDetail" property in "Instructor" class, it will look for instructorDetail and find instructor that has the same value as join column i.e. instructor_detail_id
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

    public InstructorDetail(){

    }
    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "instructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
