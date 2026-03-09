package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ioc_md02.business.IInvoiceService;
import ioc_md02.dao.impl.CustomerDAOImpl;
import ioc_md02.dao.impl.InvoiceDAOImpl;
import ioc_md02.model.Invoice;
import ioc_md02.model.InvoiceDetail;

public class InvoiceServiceImpl implements IInvoiceService{
    private static InvoiceServiceImpl instance;


    private InvoiceServiceImpl() {}

    public static InvoiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceServiceImpl();
        }
        return instance;
    }

    @Override
    public void addInvoice(Scanner scanner) {
        System.out.println();
        System.out.println("========== THÊM HÓA ĐƠN MỚI ==========");

        Invoice invoice = new Invoice();
        invoice.inputData(scanner);

        if (InvoiceDAOImpl.getInstance().addInvoice(invoice)) {
            System.out.println("Thêm hóa đơn mới thành công!");
        } else {
            System.out.println("Thêm hóa đơn mới thất bại!");
        }
    }

    @Override
    public void getAllInvoices(Scanner scanner) {
        ResultSet invoices = InvoiceDAOImpl.getInstance().getAllInvoices();

        try {
            if (!invoices.next()) {
                System.out.println("Không có hóa đơn nào.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayInvoices(invoices);
        InvoiceDetailServiceImpl.getInstance().getInvoiceDetailsByInvoiceId(scanner);

    }

    @Override
    public void getInvoicesByCustomerName(Scanner scanner) {
        System.out.println();
        System.out.println("=============== TÌM HÓA ĐƠN THEO TÊN KHÁCH HÀNG ===============");

        String filterName;

        while (true) {
            System.out.print("Nhập tên khách hàng muốn tìm: ");
            filterName = scanner.nextLine();
            if (!filterName.isEmpty()) {
                break;
            }
            System.out.println("Tên khách hàng không được để trống!");
        }

        ResultSet invoices = InvoiceDAOImpl.getInstance().getInvoicesByCustomerName(filterName);
        try {
            if (!invoices.next()) {
                System.out.println("Không tìm thấy hóa đơn nào của khách hàng " + filterName);
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayInvoices(invoices);
        InvoiceDetailServiceImpl.getInstance().getInvoiceDetailsByInvoiceId(scanner);
    }

    @Override
    public void getInvoicesByDate(Scanner scanner) {
        System.out.println();
        System.out.println("=============== TÌM HÓA ĐƠN THEO NGÀY TẠO HÓA ĐƠN ===============");

        LocalDate filterDate;

        while (true) {
            try {
                System.out.print("Nhập ngày muốn tìm(dd/mm/yyyy): ");
                filterDate = LocalDate.parse(scanner.nextLine(), Invoice.formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ!");
            }
        }

        ResultSet invoices = InvoiceDAOImpl.getInstance().getInvoicesByDate(filterDate);
        try {
            if (!invoices.next()) {
                System.out.println("Không tìm thấy hóa đơn nào trong ngày " + filterDate.format(Invoice.formatter));
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayInvoices(invoices);
        InvoiceDetailServiceImpl.getInstance().getInvoiceDetailsByInvoiceId(scanner);
    }

    @Override
    public Invoice getInvoicesById(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvoicesById'");
    }

    @Override
    public void displayInvoices(ResultSet rs) {
        Invoice invoice;
        System.out.println();
        System.out.println(
                "+--------------------------------- DANH SÁCH HÓA ĐƠN ---------------------------------+");
        System.out.printf("|%-5s|%-50s|%-15s|%-12s|\n", "ID", "KHÁCH HÀNG", "NGÀY TẠO", "TỔNG TIỀN");
        System.out.println(
                "+-----+--------------------------------------------------+---------------+------------+");
        try {
            do {
                invoice = new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount"));
                System.out.println(invoice);
            } while (rs.next());
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm: " + e.getMessage());
        } finally {
            System.out.println(
                "+-------------------------------------------------------------------------------------+");
        }

    }

    @Override
    public void displayInvoices(Invoice invoice) {
        System.out.println();
        System.out.println("=============== HÓA ĐƠN ===============");
        System.out.printf("Mã hóa đơn:\t%d\n", invoice.getId());
        System.out.printf("Khách hàng:\t%s\n", CustomerDAOImpl.getInstance().getCustomerById(invoice.getCustomerId()).getName());
        System.out.printf("Ngày tạo:\t%s\n", invoice.getCreatedAt().format(Invoice.formatter));
        System.out.println("+--------------------------------------------------+------------+----------+---------------+");
        System.out.printf("|%-50s|%-12s|%-10s|%-15s|\n", "TÊN ĐIỆN THOẠI", "ĐƠN GIÁ", "SỐ LƯỢNG", "THÀNH TIỀN");
        System.out.println("+--------------------------------------------------+------------+----------+---------------+");
        for (InvoiceDetail item : invoice.getItems()) {
            System.out.println(item);
            System.out.println("+--------------------------------------------------+------------+----------+---------------+");
        }
        System.out.printf("TỔNG HÓA ĐƠN: %.2f\n", invoice.getTotalAmount());
        System.out.println();
    }


}
