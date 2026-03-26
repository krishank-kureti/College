class StudentBad {
    private String name;
    private String email;
    private double[] grades;
    
    public StudentBad(String name, String email) {
        this.name = name;
        this.email = email;
        this.grades = new double[0];
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public double calculateGPA() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return grades.length > 0 ? sum / grades.length : 0;
    }
    
    public String generateReport() {
        return "Student: " + name + ", GPA: " + calculateGPA();
    }
    
    public void sendEmailNotification(String message) {
        System.out.println("Sending email to " + email + ": " + message);
    }
    
    public void addGrade(double grade) {
        double[] newGrades = new double[grades.length + 1];
        System.arraycopy(grades, 0, newGrades, 0, grades.length);
        newGrades[grades.length] = grade;
        this.grades = newGrades;
    }
}

class Student {
    private String id;
    private String name;
    private String email;
    private double[] grades;
    
    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grades = new double[0];
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void addGrade(double grade) {
        double[] newGrades = new double[grades.length + 1];
        System.arraycopy(grades, 0, newGrades, 0, grades.length);
        newGrades[grades.length] = grade;
        this.grades = newGrades;
    }
    
    public double[] getGrades() {
        return grades;
    }
}

class GradeCalculator {
    public double calculateGPA(Student student) {
        double[] grades = student.getGrades();
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return grades.length > 0 ? sum / grades.length : 0;
    }
    
    public String getLetterGrade(double gpa) {
        if (gpa >= 3.7) return "A";
        if (gpa >= 3.3) return "A-";
        if (gpa >= 3.0) return "B+";
        if (gpa >= 2.7) return "B";
        if (gpa >= 2.3) return "B-";
        if (gpa >= 2.0) return "C+";
        return "C";
    }
}

class ReportGenerator {
    private GradeCalculator gradeCalculator;
    
    public ReportGenerator(GradeCalculator gradeCalculator) {
        this.gradeCalculator = gradeCalculator;
    }
    
    public String generateReport(Student student) {
        double gpa = gradeCalculator.calculateGPA(student);
        String letterGrade = gradeCalculator.getLetterGrade(gpa);
        return "Student: " + student.getName() + 
               "\nID: " + student.getId() + 
               "\nGPA: " + String.format("%.2f", gpa) + 
               "\nLetter Grade: " + letterGrade;
    }
}

class NotificationService {
    public void sendEmailNotification(Student student, String message) {
        System.out.println("Email sent to " + student.getEmail() + ": " + message);
    }
    
    public void sendSMSNotification(String phoneNumber, String message) {
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
    }
}

public class SRP_StudentManagementSystem {
    public static void main(String[] args) {
        System.out.println("===== Single Responsibility Principle (SRP) =====\n");
        
        Student student = new Student("S001", "John Doe", "john@example.com");
        student.addGrade(3.5);
        student.addGrade(3.8);
        student.addGrade(3.9);
        student.addGrade(3.6);
        
        GradeCalculator calculator = new GradeCalculator();
        double gpa = calculator.calculateGPA(student);
        System.out.println("GPA calculated: " + String.format("%.2f", gpa));
        
        ReportGenerator reportGenerator = new ReportGenerator(calculator);
        String report = reportGenerator.generateReport(student);
        System.out.println("\n" + report);
        
        NotificationService notificationService = new NotificationService();
        notificationService.sendEmailNotification(student, 
            "Your semester grades have been processed. GPA: " + String.format("%.2f", gpa));
    }
}
