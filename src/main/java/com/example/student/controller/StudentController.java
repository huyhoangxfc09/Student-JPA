package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.model.StudentForm;
import com.example.student.service.ICrudService;
import com.example.student.service.impl.IClassroomService;
import com.example.student.service.impl.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IClassroomService classroomService;
    @Autowired
    private IStudentService studentService;
    @GetMapping
    private String listStudent(Model model){
        List<Student> student = studentService.findAll();
        model.addAttribute("student",student);
        return "student/list";
    }
    @GetMapping("/create")
    private ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("studentForm",new StudentForm());
        modelAndView.addObject("classroom",classroomService.findAll());
        return modelAndView;
    }
    @PostMapping("/create")
    private String createStudent(@ModelAttribute StudentForm studentForm, RedirectAttributes attributes){
        MultipartFile multipartFile = studentForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(studentForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Student student = new Student(studentForm.getId(),fileName, studentForm.getName(),studentForm.getAge(),
                studentForm.getAddress(),studentForm.getClassroom()
                );
        studentService.create(student);
        attributes.addFlashAttribute("message","Tạo mới thành công");
        return "redirect:/students";
    }
   
}
