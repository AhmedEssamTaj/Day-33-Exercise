package com.example.day33exercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5,message = "name length must be more then 4")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "CHAR_LENGTH(name) > 4")
    private String name;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 5,message = "username length must be more then 4")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "CHAR_LENGTH(username) > 4")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "must be a valid email format")
    @Column(columnDefinition = "varchar(30) not null unique")
    @Check(constraints = "CHAR_LENGTH(username) > 4")
    private String email;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(user|admin)$",message = "role must be either a user or admin")
    @Column(columnDefinition = "varchar(5) not null")
    @Check(constraints = "role regexp '^(user|admin)$'")
    private String role;

    @NotNull(message = "age cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
