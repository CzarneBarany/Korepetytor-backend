package com.models;

import lombok.Data;

@Data
public class SessionModel {
    private Role role;
    private String jwtToken;
    private int accountId;
}
