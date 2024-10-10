package cm.estudiantes.estudiantes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
// boilerplate - repetitivo
@Table(name = "estudiante") //--> Al tener la tabla en BBDD nombre en español tengo que recalcarlo
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante") //--> Tengo que poner esto debido a que en la tabla en BBDD está en español
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
