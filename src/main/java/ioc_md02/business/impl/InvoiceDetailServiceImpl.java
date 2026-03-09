package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import ioc_md02.business.IInvoiceDetailService;
import ioc_md02.dao.impl.InvoiceDAOImpl;
import ioc_md02.dao.impl.InvoiceDetailDAOImpl;
import ioc_md02.model.Invoice;
import ioc_md02.model.InvoiceDetail;

public class InvoiceDetailServiceImpl implements IInvoiceDetailService{
    private static InvoiceDetailServiceImpl instance;

    private InvoiceDetailServiceImpl() {}

    public static InvoiceDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceDetailServiceImpl();
        }
        return instance;
    }

    @Override
    public void addInvoiceDetail(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addInvoiceDetail'");
    }

    @Override
    public void getInvoiceDetailsByInvoiceId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID của hóa đơn muốn xem chi tiết (Nhập 'Exit' để trở về): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                return;
            }

            try {
                int invoiceId = Integer.parseInt(input);
                Invoice invoice = InvoiceDAOImpl.getInstance().getInvoicesById(invoiceId);
                ResultSet rs = InvoiceDetailDAOImpl.getInstance().getInvoiceDetailsByInvoiceId(invoiceId);
                ArrayList<InvoiceDetail> items = new ArrayList<>();

                if (invoice == null) {
                    System.out.println("Không tồn tại hóa đơn có ID = " + invoiceId);
                    continue;
                }

                while (rs.next()) {
                    InvoiceDetail invoiceDetail = new InvoiceDetail();

                    invoiceDetail.setId(rs.getInt("id"));
                    invoiceDetail.setInvoiceId(rs.getInt("invoice_id"));
                    invoiceDetail.setProductId(rs.getInt("product_id"));
                    invoiceDetail.setQuantity(rs.getInt("quantity"));
                    invoiceDetail.setUnitPrice(rs.getDouble("unit_price"));

                    items.add(invoiceDetail);
                }

                invoice.setItems(items);
                InvoiceServiceImpl.getInstance().displayInvoices(invoice);

            } catch (IllegalArgumentException e) {
                System.out.println("Lựa chọn không hợp lệ!");

            } catch (SQLException e) {
                System.out.println("Lỗi SQL!");
            }

        }
    }

}
