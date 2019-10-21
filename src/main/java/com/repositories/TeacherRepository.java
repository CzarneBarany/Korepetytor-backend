package com.repositories;

import com.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    TeacherEntity getTeacherEntityByTeacherId(int teacherId);
}
