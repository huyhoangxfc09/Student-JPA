package com.example.student.repository;
import com.example.student.model.Classroom;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class ClassroomRepository implements IClassroomRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Classroom> findAll() {
        TypedQuery<Classroom> query = em.createQuery("select c from Classroom c", Classroom.class);
        return query.getResultList();

    }

    @Override
    public Classroom findById(Long id) {
        TypedQuery<Classroom> query = em.createQuery("select c from Classroom c where  c.id=:id", Classroom.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void create(Classroom classroom) {
        em.persist(classroom);
    }

    @Override
    public void update(Classroom classroom) {
        em.merge(classroom);
    }

    @Override
    public void remove(Long id) {
        Classroom classroom = findById(id);
        if (classroom != null) {
            em.remove(classroom);
        }
    }
}
