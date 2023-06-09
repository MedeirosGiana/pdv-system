package com.gm.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Name field is required.")
    private String name;
    @NotBlank(message = "UserName field is required.")
    private String username;
    @NotBlank(message = "Password field is required.")
    private String password;
    private boolean isEnabled;
}
