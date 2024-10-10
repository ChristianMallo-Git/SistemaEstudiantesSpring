package cm.estudiantes.estudiantes.repository;

import cm.estudiantes.estudiantes.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,Integer> {
}
