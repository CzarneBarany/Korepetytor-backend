package com.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEntity {

    @Id
    private int teacherId;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    private int age;

    private String levelOfEducation;

    private List<UserEntity> listOfStudents = new ArrayList<>();
}
