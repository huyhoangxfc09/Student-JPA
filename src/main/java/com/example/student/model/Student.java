package com.example.student.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagePath;
    @Column

    private String name;
    @Column

    private int age;
    @Column

    private String address;

    @ManyToOne
    private Classroom classroom;
    @Transient
    private MultipartFile image;

    public Student() {
    }

    public Student(Long id, String imagePath, String name, int age, String address, Classroom classroom) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.age = age;
        this.address = address;
        this.classroom = classroom;
    }

    public Student(Long id, String imagePath, String name, int age, String address, Classroom classroom, MultipartFile image) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.age = age;
        this.address = address;
        this.classroom = classroom;
        this.image = image;
    }

    public Student(String imagePath, String name, int age, String address, Classroom classroom, MultipartFile image) {
        this.imagePath = imagePath;
        this.name = name;
        this.age = age;
        this.address = address;
        this.classroom = classroom;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
