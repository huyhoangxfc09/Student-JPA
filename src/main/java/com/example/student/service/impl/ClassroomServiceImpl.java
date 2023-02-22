package com.example.student.service.impl;

import com.example.student.model.Classroom;
import com.example.student.repository.IClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements IClassroomService{
    @Autowired
    private IClassroomRepository classroomRepository;
    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom findById(Long id) {
        return classroomRepository.findById(id);
    }

    @Override
    public void create(Classroom classroom) {
        classroomRepository.create(classroom);
    }
    @Override
    public void update(Classroom classroom) {
        classroomRepository.update(classroom);
    }

    @Override
    public void remove(Long id) {
        classroomRepository.remove(id);
    }
}
