package ioc_md02.presentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ioc_md02.dao.impl.InvoiceDetailDAOImpl;
import ioc_md02.model.InvoiceDetail;

public class InvoiceDetailView {
    private static InvoiceDetailView instance;

    private InvoiceDetailView() {}

    public static InvoiceDetailView getInstance() {
        if (instance == null) {
            instance = new InvoiceDetailView();
        }
        return instance;
    }

    public void showInvoiceDetails(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập ID của hóa đơn muốn xem chi tiết (Nhập 'Exit' để trở về): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    return;
                }
                int invoiceId = Integer.parseInt(input);


            } catch (IllegalArgumentException e) {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
