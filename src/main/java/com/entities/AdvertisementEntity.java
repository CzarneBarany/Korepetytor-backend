package com.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    private AccountEntity teacher;

    @ElementCollection(targetClass = AccountEntity.class)
    List<AccountEntity> listOfStudents = new ArrayList<>();
}
