package edu.icet.dto;

import edu.icet.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class RegisterRequest {

    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<Role> roles;

}
