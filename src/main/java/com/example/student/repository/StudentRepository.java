package com.example.student.repository;

import com.example.student.model.Classroom;
import com.example.student.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        TypedQuery<Student> query = em.createQuery("select s from Student s where  s.id=:id", Student.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void create(Student student) {
        em.persist(student);
    }

    @Override
    public void update(Student student) {
        em.merge(student);
    }

    @Override
    public void remove(Long id) {
        Student student = findById(id);
        if (student != null) {
            em.remove(student);
        }
    }
}
