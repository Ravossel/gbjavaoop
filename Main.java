import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Модель
class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

// Сервис для управления данными о преподавателях
class TeacherService {
    private List<Teacher> teachers;

    public TeacherService() {
        this.teachers = new ArrayList<>();
    }

    public void addTeacher(String name, String subject) {
        teachers.add(new Teacher(name, subject));
    }

    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers);
    }

    public void updateTeacherSubject(String name, String newSubject) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
                teacher.setSubject(newSubject);
                break;
            }
        }
    }
}

// Представление
class TeacherView {
    public void printTeacherDetails(Teacher teacher) {
        System.out.println("Teacher: " + teacher.getName() + ", Subject: " + teacher.getSubject());
    }

    public void printTeachersList(List<Teacher> teachers) {
        System.out.println("Teachers List:");
        for (Teacher teacher : teachers) {
            printTeacherDetails(teacher);
        }
    }
}

// Контроллер
class TeacherController {
    private TeacherService teacherService;
    private TeacherView teacherView;

    public TeacherController(TeacherService teacherService, TeacherView teacherView) {
        this.teacherService = teacherService;
        this.teacherView = teacherView;
    }

    public void createTeacher(String name, String subject) {
        teacherService.addTeacher(name, subject);
    }

    public void updateTeacherSubject(String name, String newSubject) {
        teacherService.updateTeacherSubject(name, newSubject);
    }

    public void displayTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        teacherView.printTeachersList(teachers);
    }
}

// Класс с методом main для запуска приложения
public class Main {
    public static void main(String[] args) {
        TeacherService teacherService = new TeacherService();
        TeacherView teacherView = new TeacherView();
        TeacherController teacherController = new TeacherController(teacherService, teacherView);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Teacher Management System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Teacher");
            System.out.println("2. Update Teacher Subject");
            System.out.println("3. Display Teachers");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter teacher name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();
                    teacherController.createTeacher(name, subject);
                    break;
                case 2:
                    System.out.print("Enter teacher name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("Enter new subject: ");
                    String newSubject = scanner.nextLine();
                    teacherController.updateTeacherSubject(teacherName, newSubject);
                    break;
                case 3:
                    teacherController.displayTeachers();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
