package com.gm.pdv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Name field is required.")
    private String name;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "UserName field is required.")
    private String username;

    @Column(length = 60, nullable = false)
    @NotBlank(message = "Password field is required.")
    private String password;

    private boolean isEnabled;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}
