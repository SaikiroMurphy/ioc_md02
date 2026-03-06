package ioc_md02.presentation;

import java.util.Scanner;

import ioc_md02.business.impl.CustomerServiceImpl;

public class CustomerView {
    private static CustomerView instance;

    private CustomerView() {}

    public static CustomerView getInstance() {
        if (instance == null) {
            instance = new CustomerView();
        }
        return instance;
    }

    public void showCustomerMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("========== QUẢN LÝ KHÁCH HÀNG ==========");
            System.out.println("1. Xem danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Quay lại menu chính");
            System.out.println("========================================");

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
                    CustomerServiceImpl.getInstance().displayAllCustomers();
                    break;
                case 2:
                    CustomerServiceImpl.getInstance().addCustomer(scanner);
                    break;
                case 3:
                    CustomerServiceImpl.getInstance().updateCustomer(scanner);
                    break;
                case 4:
                    CustomerServiceImpl.getInstance().deleteCustomer(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
