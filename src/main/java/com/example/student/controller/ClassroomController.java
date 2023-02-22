package com.example.student.controller;

import com.example.student.model.Classroom;
import com.example.student.service.ICrudService;
import com.example.student.service.impl.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {
    @Autowired
    private IClassroomService classroomService;
    @GetMapping
    public String listClassroom(Model model){
        List<Classroom> classrooms = classroomService.findAll();
        model.addAttribute("classroom",classrooms);
        return "classroom/list";
    }
    @GetMapping("create")
    private ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("classroom/create");
        modelAndView.addObject("classroom", new Classroom());
        return modelAndView;
    }
    @PostMapping("/create")
    private String createClassroom(@ModelAttribute Classroom classroom, RedirectAttributes attributes){
        classroomService.create(classroom);
        attributes.addFlashAttribute("message","Tạo mới thành công");
        return "redirect:/classrooms";
    }
    @GetMapping("/update/{id}")
    private ModelAndView updateForm(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("classroom/update");
        modelAndView.addObject("classroom",classroomService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update/{id}")
    private String updateClassroom(@ModelAttribute Classroom classroom, RedirectAttributes attributes){
        classroomService.update(classroom);
        attributes.addFlashAttribute("message","Update thành công");
        return "redirect:/classrooms";
    }
    @GetMapping("/delete/{id}")
    private String deleteClassroom(@PathVariable("id")Long id){
        classroomService.remove(id);
        return "redirect:/classrooms";
    }
}
