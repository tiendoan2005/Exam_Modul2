import java.io.*;
import java.util.*;

public class TeacherManager {
    private static final String TEACHER_FILE = "data/teachers.csv";
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
                    System.out.println("Lỗi dữ liệu giáo viên: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi tải danh sách giáo viên: " + e.getMessage());
        }
    }
    
    public void displayTeachersFromFile() {
        File file = new File(TEACHER_FILE);
        if (!file.exists()) {
            System.out.println("File danh sách giáo viên không tồn tại.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- DANH SÁCH GIÁO VIÊN ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

}
