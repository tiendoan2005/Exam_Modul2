public class Student extends Person {
    private int studentId;
    private String classId;

    public Student(int studentId, String name, String birthDate, String gender, String phoneNumber, String classId) {
        super(name, birthDate, gender, phoneNumber);
        this.studentId = studentId;
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }


    public String toCSV() {
        return studentId + "," + name + "," + birthDate + "," + gender + "," + phoneNumber + "," + classId;
    }

    @Override
    public void displayInfo() {
        System.out.printf("| %-5d | %-20s | %-10s | %-5s | %-12s | %-10s |\n",
                studentId, name, birthDate, gender, phoneNumber, classId);
    }
}
