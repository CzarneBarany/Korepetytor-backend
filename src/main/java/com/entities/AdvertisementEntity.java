package com.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AdvertisementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String adCategory;

    @NotBlank
    private String adLevelOfEducation;

    private int pricePerHour;

    @OneToOne(cascade = CascadeType.ALL)
    private AccountEntity teacher;

    @OneToMany(cascade = CascadeType.ALL)
    List<AccountEntity> listOfStudents = new ArrayList<>();
}
