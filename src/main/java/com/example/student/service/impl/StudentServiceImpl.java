package com.example.student.service.impl;

import com.example.student.model.Student;
import com.example.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class StudentServiceImpl implements IStudentService{
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    IStudentRepository studentRepository;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void create(Student student) {
        student.setImagePath(getFileName(student));
        studentRepository.create(student);
    }

    @Override
    public void update(Student student) {
        student.setImagePath(getFileName(student));
        studentRepository.update(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.remove(id);
    }
    public String getFileName(Student student) {
        MultipartFile image = student.getImage();
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
}
