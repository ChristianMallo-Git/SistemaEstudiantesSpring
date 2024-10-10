package cm.estudiantes.estudiantes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idStudent;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String surName;
    @Column(name = "telefono")
    private Integer phoneNumber;
    @Column(name = "email")
    private String email;
}
