package com.services;

import com.entities.TeacherEntity;
import com.exceptions.EntityNotFoundException;
import com.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void addTeacher(TeacherEntity teacherEntity) {
        teacherRepository.save(teacherEntity);
    }

    public TeacherEntity getTeacherById(int teacherId) {
        TeacherEntity teacher = teacherRepository.getTeacherEntityByTeacherId(teacherId);
        if (teacher != null) {
            return teacher;
        } else {
            throw new EntityNotFoundException("Nie znaleziono korepetytora o takim id" + teacherId);
        }
    }
}
