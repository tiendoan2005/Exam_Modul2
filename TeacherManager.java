import java.io.*;
import java.util.*;

public class TeacherManager {
    private static final String TEACHER_FILE = "data/teacher.csv";
    private List<Teacher> teachers = new ArrayList<>();

    public TeacherManager() {
        loadTeachers();
    }

    private void loadTeachers() {
        File file = new File(TEACHER_FILE);
        if (!file.exists()) {
            System.out.println("Chưa có dữ liệu giáo viên.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    teachers.add(new Teacher(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim()));
                } else {
                    System.out.println("Lỗi dữ liệu giáo viên, bỏ qua dòng: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file giáo viên: " + e.getMessage());
        }
    }

    public void displayTeacherById(String teacherId) {
        System.out.println("\nĐang tìm kiếm giáo viên...");
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equalsIgnoreCase(teacherId)) {
                System.out.println("\n================================================================================");
                System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-15s |\n", "Mã GV", "Tên", "Ngày sinh", "Giới tính", "Số điện thoại");
                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-15s |\n",
                        teacher.getTeacherId(), teacher.getName(), teacher.getBirthDate(), teacher.getGender(), teacher.getPhoneNumber());
                System.out.println("================================================================================\n");
                return;
            }
        }
        System.out.println("Không tìm thấy giáo viên với mã: " + teacherId + "\n");
    }
}