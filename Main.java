import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        TeacherManager teacherManager = new TeacherManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Xóa sinh viên");
            System.out.println("3. Sửa thông tin sinh viên");
            System.out.println("4. Hiển thị danh sách sinh viên");
            System.out.println("5. Xuất danh sách sinh viên ra CSV");
            System.out.println("6. Tìm kiếm sinh viên theo tên");
            System.out.println("7. Hiển thị thông tin giáo viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Đọc dòng mới

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
                    String birthDate = sc.nextLine();
                    System.out.print("Nhập giới tính: ");
                    String gender = sc.nextLine();
                    System.out.print("Nhập SĐT: ");
                    String phoneNumber = sc.nextLine();
                    System.out.print("Nhập mã lớp: ");
                    String classId = sc.nextLine();
                    studentManager.addStudent(name, birthDate, gender, phoneNumber, classId);
                    break;

                case 2:
                    while (true) {
                        try {
                            System.out.print("Nhập mã sinh viên cần xóa (Nhấn Enter để quay lại menu): ");
                            String input = sc.nextLine().trim();
                            if (input.isEmpty())
                                break;
                            int deleteId = Integer.parseInt(input);
                            studentManager.deleteStudent(sc, deleteId);
                            break;
                        } catch (NotFoundStudentException e) {
                            System.out.println(e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Mã sinh viên phải là số nguyên hợp lệ.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã sinh viên cần sửa: ");
                    int editId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhập tên mới: ");
                    String newName = sc.nextLine();
                    System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                    String newBirthDate = sc.nextLine();
                    System.out.print("Nhập giới tính mới: ");
                    String newGender = sc.nextLine();
                    System.out.print("Nhập SĐT mới: ");
                    String newPhoneNumber = sc.nextLine();
                    System.out.print("Nhập mã lớp mới: ");
                    String newClassId = sc.nextLine();
                    studentManager.editStudent(editId, newName, newBirthDate, newGender, newPhoneNumber, newClassId);
                    break;

                case 4:
                    studentManager.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Nhập file cần xuất: ");
                    String fileName = sc.nextLine();
                    studentManager.exportCSV(fileName);
                    break;

                case 6:
                    System.out.print("Nhập tên sinh viên cần tìm: ");
                    String keyword = sc.nextLine();
                    studentManager.searchStudent(keyword);
                    break;

                case 7:
                    System.out.print("Nhập mã giáo viên: ");
                    String teacherId = sc.nextLine();
                    teacherManager.displayTeacherById(teacherId);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
