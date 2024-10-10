package cm.estudiantes.estudiantes;

import cm.estudiantes.estudiantes.model.Student;
import cm.estudiantes.estudiantes.service.StudentService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(StudentsApplication.class);
	//Al utilizar Spring, en vez de System usamos Logger

	private String nl = System.lineSeparator();

	private static final Scanner console = new Scanner(System.in);

	private int optionSelected;


	public static void main(String[] args) {
		logger.info("Starting the application...");
		//Levantar la fabrica de Spring
		SpringApplication.run(StudentsApplication.class, args);
		logger.info("Application completed!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Executing spring's run method" + nl);

		logger.info(nl + "Welcome to the Student System Application!" + nl);
		integrateLoop();

	}

	private void integrateLoop() {
		do {
			try {
				logger.info(nl);
				showMenu();
				optionSelected = console.nextInt();
				if (optionSelected >= 1 && optionSelected < 7) {
					manageAction(optionSelected);
				} else {
					logger.info("Invalid option. Please select a number between 1 and 6." + nl);
				}
			} catch (InputMismatchException e) {
				logger.info("An unexpected error occurred: Invalid input, cannot be a character/string. Please try again." + nl);
				console.next();  //-->Al introducir un dato erroneo de numérico, el objeto Scanner queda en un
				//estado inconsistente, no avanza a la siguiente entrada. Si no hacemos nada por limpiar dicha
				//entrada se volvera a repetir en el siguiente ciclo del bucle. El método console.next(); permite
				//consumir dicha entrada incorrecta permitiendo que el Scanner avance a la siguiente entrada.
			}
		} while (true);
	}

	private void showMenu() {
		logger.info("""
						*** Students System App ***
						1.List students
						2.Search students
						3.Insert student
						4.Modify student
						5.Delete student
						6.Exit
						¿What you want to do?:
				""");
	}

	private void manageAction(int optionSelected) {
		switch (optionSelected) {
			case 1:
				listStudent();
				break;
			case 2:
				searchStudent();
				break;
			case 3:
				addStudent();
				break;
			case 4:
				modifyStudent();
				break;
			case 5:
				deleteStudent();
			case 6:
				System.out.println("You have made a successful exit, we look forward to seeing you again.");
				System.exit(0);
				break;
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void listStudent() {
		logger.info(nl + "List of Students" + nl);
		List<Student> students = studentService.listStudent();
		students.forEach((Student -> logger.info(Student.toString() + nl)));
	}

	private void searchStudent() {
		logger.info("Enter the id of the student to search for:");
		int idStudent = console.nextInt();
		Student student = studentService.searchStudentById(idStudent);

		if (student != null) {
			logger.info("Student found: " + student + nl);
		} else {
			logger.info("Student with id: " + idStudent + "not found" + nl);
		}
	}

	private void addStudent() {
		try {
			console.nextLine();
			logger.info("Student name to add: " + nl);
			String name = console.nextLine();
			if (isNumeric(name)) {
				throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
			}
			logger.info("Student surname to add: " + nl);
			String surName = console.nextLine();
			if (isNumeric(surName)) {
				throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
			}
			logger.info("Student phone number to add: " + nl);
			int phoneNumber = console.nextInt();
			console.nextLine();
			logger.info("Student email to add: " + nl);
			String email = console.nextLine();
			if (isNumeric(email)) {
				throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
			}

			Student student = new Student();
			student.setName(name);
			student.setSurName(surName);
			student.setPhoneNumber(phoneNumber);
			student.setEmail(email);
			studentService.saveStudent(student);
			logger.info("Aggregated student: " + student + nl);

		} catch (IllegalArgumentException e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	private void modifyStudent() {
		logger.info("Enter the id of the student you want to modify: " + nl);
		try {
			int idStudent = console.nextInt();
			Student student = studentService.searchStudentById(idStudent);
			if (student != null) {
				console.nextLine();
				logger.info("Enter a new name: " + nl);
				String name = console.nextLine();
				if (isNumeric(name)) {
					throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
				}
				logger.info("Enter a new surname: " + nl);
				String surName = console.nextLine();
				if (isNumeric(surName)) {
					throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
				}
				logger.info("Enter a new phone number: " + nl);
				int phoneNumber = console.nextInt();
				console.nextLine();
				logger.info("Enter a new email: " + nl);
				String email = console.nextLine();
				if (isNumeric(email)) {
					throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
				}

				student.setName(name);
				student.setSurName(surName);
				student.setPhoneNumber(phoneNumber);
				student.setEmail(email);

				studentService.saveStudent(student);
				logger.info("Modified student: " + student + nl);

			} else {
				logger.info("Student not found" + nl);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	private void deleteStudent() {
		logger.info("Enter the id of the student you want to delete: " + nl);
		int idStudent = console.nextInt();
		Student student = studentService.searchStudentById(idStudent);
		if (student != null) {
			studentService.deleteStudent(student);
			logger.info("The student " + student + " has been successfully deleted" + nl);
			integrateLoop();
		} else {
			logger.info("Student not found" + nl);
		}

	}
}
