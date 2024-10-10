package cm.estudiantes.estudiantes.service;

import cm.estudiantes.estudiantes.model.Student;
import cm.estudiantes.estudiantes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listStudent() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @Override
    public Student searchStudentById(Integer idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        return student;
        //El método findById devuelve un objeto de tipo Optional, tenemos que indicar
        //qué queremos que suceda en caso que el valor que se regrese sea nulo o si se
        //ocasiona una Exception
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
