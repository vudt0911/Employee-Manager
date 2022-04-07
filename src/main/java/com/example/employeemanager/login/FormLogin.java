package com.example.employeemanager.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormLogin {
    private int id;
    private String email;
    private String password;
}
