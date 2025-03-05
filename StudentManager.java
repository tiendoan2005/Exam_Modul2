import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String STUDENT_FILE = "data/students.csv";
    private static final String CLASS_FILE = "data/batchs.csv";
    private List<Student> students = new ArrayList<>();

    public StudentManager() {
        loadStudents();
    }

    private void loadStudents() {
        File file = new File(STUDENT_FILE);
        if (!file.exists()) {
            System.out.println("Chưa có dữ liệu sinh viên.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) { // Đảm bảo đúng số lượng cột
                    try {
                        int id = Integer.parseInt(data[0].trim());
                        students.add(new Student(id, data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng ID sinh viên trong file: " + line);
                    }
                } else {
                    System.out.println("Dữ liệu không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi tải danh sách sinh viên: " + e.getMessage());
        }
    }

    private boolean isPhoneUnique(String phoneNumber) {
        for (Student s : students) {
            if (s.getPhoneNumber().equals(phoneNumber)) return false;
        }
        return true;
    }

    private boolean isClassIdValid(String classId) {
        File file = new File(CLASS_FILE);
        if (!file.exists()) {
            System.out.println("Lỗi: Không tìm thấy file lớp học. Hãy kiểm tra lại đường dẫn.");
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].trim().equals(classId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi tải dữ liệu lớp học: " + e.getMessage());
        }
        System.out.println("Mã lớp học không tồn tại.");
        return false;
    }



    public void addStudent(String name, String birthDate, String gender, String phoneNumber, String classId) {
        if (name.length() < 4 || name.length() > 50) {
            System.out.println("Tên phải từ 4 đến 50 ký tự.");
            return;
        }
        if (!birthDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Định dạng ngày sinh không hợp lệ. Sử dụng dd/MM/yyyy.");
            return;
        }
        if (!phoneNumber.matches("0\\d{9,10}")) {
            System.out.println("Số điện thoại phải bắt đầu bằng số 0 và có từ 10 đến 11 chữ số.");
            return;
        }
        if (!isPhoneUnique(phoneNumber)) {
            System.out.println("Số điện thoại đã tồn tại.");
            return;
        }
        if (!isClassIdValid(classId)) {
            System.out.println("Mã lớp học không tồn tại.");
            return;
        }

        int newId = students.isEmpty() ? 1 : students.get(students.size() - 1).getStudentId() + 1;
        Student student = new Student(newId, name, birthDate, gender, phoneNumber, classId);
        students.add(student);
        saveStudents();
        System.out.println("Thêm sinh viên thành công!");
    }

    public void deleteStudent(int id) {
        Iterator<Student> iterator = students.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId() == id) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (found) {
            saveStudents();
            System.out.println("Xóa sinh viên thành công.");
        } else {
            System.out.println("Không tìm thấy sinh viên với mã " + id);
        }
    }

    private void saveStudents() {
        File file = new File(STUDENT_FILE);
        file.getParentFile().mkdirs(); // Tạo thư mục nếu chưa có

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
            System.out.println("Dữ liệu sinh viên đã được lưu.");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu danh sách sinh viên: " + e.getMessage());
        }
    }


    public void exportCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Mã SV,Tên,Ngày sinh,Giới tính,SĐT,Mã lớp");
            bw.newLine();
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
            System.out.println("Xuất file CSV thành công: " + filePath);
        } catch (IOException e) {
            System.out.println("Lỗi khi xuất file CSV: " + e.getMessage());
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            students.forEach(Student::displayInfo);
        }
    }
    public void editStudent(int id, String name, String birthDate, String gender, String phoneNumber, String classId) {
        for (Student student : students) {
            if (student.getStudentId() == id) {
                student.setName(name);
                student.setBirthDate(birthDate);
                student.setGender(gender);
                student.setPhoneNumber(phoneNumber);
                student.setClassId(classId);
                saveStudents();
                System.out.println("Cập nhật thông tin sinh viên thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên với mã " + id);
    }

    public void searchStudent(String keyword) {
        boolean found = false;
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                student.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên nào!");
        }
    }

}
