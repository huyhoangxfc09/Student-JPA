package com.example.student.controller;

import com.example.student.model.Student;
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
        modelAndView.addObject("student",new Student());
        modelAndView.addObject("classroom",classroomService.findAll());
        return modelAndView;
    }
    @PostMapping("/create")
    private String createStudent(@ModelAttribute Student student, RedirectAttributes attributes){
        studentService.create(student);
        attributes.addFlashAttribute("message","Tạo mới thành công");
        return "redirect:/students";
    }
    @GetMapping("/update/{id}")
    private ModelAndView updateForm(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("student/update");
        modelAndView.addObject("student",studentService.findById(id));
        modelAndView.addObject("classroom",classroomService.findAll());
        return modelAndView;
    }
    @PostMapping("/update/{id}")
    private String updateStudent(@ModelAttribute Student student, RedirectAttributes attributes){
        studentService.update(student);
        attributes.addFlashAttribute("message","Update thành công");
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    private String deleteClassroom(@PathVariable("id")Long id){
        studentService.remove(id);
        return "redirect:/students";
    }
   
}
