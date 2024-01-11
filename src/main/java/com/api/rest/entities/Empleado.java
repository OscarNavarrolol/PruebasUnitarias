package com.api.rest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="empleado")
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "nombre",nullable = false)
    private String nombre;
    @Column (name = "apellido",nullable = false)
    private String apellido;
    @Column (name = "email",nullable = false)
    private String email;
}
