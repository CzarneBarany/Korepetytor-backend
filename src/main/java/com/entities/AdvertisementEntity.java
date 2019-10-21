package com.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementEntity {

    @Id
    private int adId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private int teacherId;

}
