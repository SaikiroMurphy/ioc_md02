package ioc_md02.presentation;

import java.util.Scanner;

import ioc_md02.business.impl.InvoiceServiceImpl;

public class InvoiceView {
    private static InvoiceView instance;

    private InvoiceView() {}

    public static InvoiceView getInstance() {
        if (instance == null) {
            instance = new InvoiceView();
        }
        return instance;
    }

    public void showInvoiceMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("============ QUẢN LÝ HÓA ĐƠN ============");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm hóa đơn mới");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("=========================================");

            int choice = -1;
            try {
                System.out.print("Vui lòng chọn (1-4): ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    InvoiceServiceImpl.getInstance().getAllInvoices(scanner);
                    break;
                case 2:
                    InvoiceServiceImpl.getInstance().addInvoice(scanner);
                    break;
                case 3:
                    findInvoiceMenu(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public void findInvoiceMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("============ TÌM KIẾM HÓA ĐƠN ============");
            System.out.println("1. Tìm kiếm theo tên khách hàng");
            System.out.println("2. Tìm kiếm theo ngày tạo hóa đơn");
            System.out.println("3. Quay lại menu hóa đơn");
            System.out.println("==========================================");

            int choice = -1;
            try {
                System.out.print("Vui lòng chọn (1-3): ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    InvoiceServiceImpl.getInstance().getInvoicesByCustomerName(scanner);
                    break;
                case 2:
                    InvoiceServiceImpl.getInstance().getInvoicesByDate(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
