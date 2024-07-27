package com.tobi.booker.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name cannot be empty")
    @NotBlank(message = "First name cannot be empty")
    private String firstname;

    @NotEmpty(message = "Last name cannot be empty")
    @NotBlank(message = "Last name cannot be empty")
    private String lastname;

    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;
}
