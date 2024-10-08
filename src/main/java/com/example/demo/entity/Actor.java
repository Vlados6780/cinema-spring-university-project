package com.example.demo.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    private int actor_id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String actor_name;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

}
