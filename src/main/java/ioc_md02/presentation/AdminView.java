package ioc_md02.presentation;

import java.util.Scanner;

import ioc_md02.business.impl.AdminServiceImpl;

public class AdminView {
    private static AdminView instance;

    private AdminView() {}

    public static AdminView getInstance() {
        if (instance == null) {
            instance = new AdminView();
        }
        return instance;
    }

    public void showStartMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("======= HỆ THỐNG QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI =======");
            System.out.printf("|%-50s|%n", "1. Đăng nhập");
            System.out.printf("|%-50s|%n", "2. Thoát");
            System.out.println("=====================================================");

            int choice = -1;
            try {
                System.out.print("Vui lòng chọn (1-2): ");
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    showLogin(scanner);
                    break;
                case 2:
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    return; // Exit the application
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý hóa đơn");
            System.out.println("3. Quản lý khách hàng");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("================================");

            int choice = -1;
            try {
                System.out.print("Vui lòng chọn (1-5): ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    ProductView.getInstance().showProductMenu(scanner);
                    break;
                case 2:
                    System.out.println("Chức năng quản lý hóa đơn đang được phát triển...");
                    break;
                case 3:
                    System.out.println("Chức năng quản lý khách hàng đang được phát triển...");
                    break;
                case 4:
                    System.out.println("Chức năng thống kê doanh thu đang được phát triển...");
                    break;
                case 5:
                    System.out.println("Đăng xuất thành công!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public void showLogin(Scanner scanner) {
        System.out.println();
        System.out.println("=============== ĐĂNG NHẬP ===============");
        AdminServiceImpl.getInstance().login(scanner);
        showMainMenu(scanner);
    }
}
