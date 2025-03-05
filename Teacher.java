public class Teacher extends Person {
    private String teacherId;

    public Teacher(String teacherId, String name, String birthDate, String gender, String phoneNumber) {
        super(name, birthDate, gender, phoneNumber);
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String getBirthDate() {
        return super.getBirthDate();
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void displayInfo() {
        System.out.println("Mã GV: " + teacherId + ", Tên: " + name + ", Ngày sinh: " + birthDate +
                ", Giới tính: " + gender + ", SĐT: " + phoneNumber);
    }
}
