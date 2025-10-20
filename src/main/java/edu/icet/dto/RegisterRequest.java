package edu.icet.dto;

import edu.icet.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {


    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<Role> roles;

}
