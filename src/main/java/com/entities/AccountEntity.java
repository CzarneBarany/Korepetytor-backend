package com.entities;

import com.models.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String city;

    private int age;

    private String phoneNumber;

    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AdvertisementEntity> myAdvertisements = new ArrayList<>();
}
