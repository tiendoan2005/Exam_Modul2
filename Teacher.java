public class Teacher extends Person {
    private String teacherId;

    public Teacher(String teacherId, String name, String birthDate, String gender, String phoneNumber) {
        super(name, birthDate, gender, phoneNumber);
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Mã GV: " + teacherId + ", Tên: " + name + ", Ngày sinh: " + birthDate +
                ", Giới tính: " + gender + ", SĐT: " + phoneNumber);
    }
}
