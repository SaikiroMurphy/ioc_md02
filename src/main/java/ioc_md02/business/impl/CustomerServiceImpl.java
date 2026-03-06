package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ioc_md02.business.ICustomerService;
import ioc_md02.dao.impl.CustomerDAOImpl;
import ioc_md02.model.Customer;

public class CustomerServiceImpl implements ICustomerService{
    private static CustomerServiceImpl instance;

    private CustomerServiceImpl() {}

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    @Override
    public void addCustomer(Scanner scanner) {
        System.out.println();
        System.out.println("========== THÊM KHÁCH HÀNG MỚI ==========");

        Customer customer = new Customer();
        customer.inputData(scanner);

        if (CustomerDAOImpl.getInstance().addCustomer(customer)) {
            System.out.println("Thêm khách hàng mới thành công!");
        } else {
            System.out.println("Thêm khách hàng mới thất bại!");
        }

    }

    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println();
        System.out.println("========== CẬP NHẬT THÔNG TIN KHÁCH HÀNG ==========");

        int updateCustomerId;
        while (true) {
            try {
                System.out.print("Nhập ID khách hàng muốn cập nhật: ");
                updateCustomerId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập số ID hợp lệ.");
            }
        }

        Customer oldCustomer = CustomerDAOImpl.getInstance().getCustomerById(updateCustomerId);

        if (oldCustomer == null) {
            System.out.println("Không tìm thấy khách hàng nào với ID phù hợp.");
            return;
        }

        displayCustomers(oldCustomer);

        Customer newCustomer = new Customer();
        newCustomer.setId(updateCustomerId);
        newCustomer.inputData(scanner);

        if (CustomerDAOImpl.getInstance().updateCustomer(updateCustomerId, newCustomer)) {
            System.out.println("Cập nhật thông tin khách hàng thành công!");
        } else {
            System.out.println("Cập nhật thông tin khách hàng thất bại!");
        }
    }

    @Override
    public void deleteCustomer(Scanner scanner) {
        System.out.println();
        System.out.println("=============== XÓA KHÁCH HÀNG ===============");

        int delCustomerId;
        while (true) {
            try {
                System.out.print("Nhập ID khách hàng muốn xóa: ");
                delCustomerId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập số ID hợp lệ.");
            }
        }

        if (CustomerDAOImpl.getInstance().deleteCustomer(delCustomerId)) {
            System.out.println("Xóa khách hàng thành công!");
        } else {
            System.out.println("Xóa khách hàng thất bại!");
        }
    }

    @Override
    public void displayAllCustomers() {
        ResultSet customers = CustomerDAOImpl.getInstance().getAllCustomers();

        try {
            if (!customers.next()) {
                System.out.println("Không có khách hàng nào.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayCustomers(customers);
    }

    @Override
    public void displayCustomers(ResultSet rs) {
        Customer customer;
        System.out.println();
        System.out.println(
                "+------------------------------------------------------------- DANH SÁCH KHÁCH HÀNG -------------------------------------------------------------+");
        System.out.println(
                "|ID   |TÊN KHÁCH HÀNG                                    |SỐ KHÁCH HÀNG  |EMAIL                                             |ĐỊA CHỈ             |");
        System.out.println(
                "+-----+--------------------------------------------------+---------------+--------------------------------------------------+--------------------+");
        try {
            do {
                customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"));
                System.out.println(customer);
            } while (rs.next());
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching khách hàng: " + e.getMessage());
        } finally {
            System.out.println(
                    "+------------------------------------------------------------------------------------------------------------------------------------------------+");
        }

    }

    @Override
    public void displayCustomers(Customer customer) {
        System.out.println();
        System.out.println(
                "+------------------------------------------------------------- DANH SÁCH KHÁCH HÀNG -------------------------------------------------------------+");
        System.out.println(
                "|ID   |TÊN KHÁCH HÀNG                                    |SỐ KHÁCH HÀNG  |EMAIL                                             |ĐỊA CHỈ             |");
        System.out.println(
                "+-----+--------------------------------------------------+---------------+--------------------------------------------------+--------------------+");
                System.out.println(customer);
        System.out.println(
                "+-----+--------------------------------------------------+---------------+--------------------------------------------------+--------------------+");

    }

}
