package cm.estudiantes.estudiantes.service;

import cm.estudiantes.estudiantes.model.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> listStudent();

    public Student searchStudentById(Integer idStudent);

    public void saveStudent(Student student);

    public void deleteStudent(Student student);



}
