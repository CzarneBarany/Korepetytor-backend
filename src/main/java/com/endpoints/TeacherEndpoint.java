package com.endpoints;

import com.entities.TeacherEntity;
import com.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TeacherEndpoint {

    private TeacherService teacherService;

    @Autowired
    public TeacherEndpoint(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/add/teacher")
    ResponseEntity addTeacherAccount(@RequestBody TeacherEntity teacherEntity){
        teacherService.addTeacher(teacherEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/allTeachers")
    ResponseEntity<List<TeacherEntity>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAllTeachers(),HttpStatus.OK);
    }

    @GetMapping("/get/teacherById")
    ResponseEntity<TeacherEntity> getTeacherById(@PathVariable int teacherId){
        return new ResponseEntity<>(teacherService.getTeacherById(teacherId),HttpStatus.OK);
    }
}
