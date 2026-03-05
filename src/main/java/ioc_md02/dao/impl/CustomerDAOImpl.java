package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ioc_md02.dao.ICustomerDAO;
import ioc_md02.model.Customer;
import ioc_md02.utils.DBUtil;

public class CustomerDAOImpl implements ICustomerDAO{
    private static CustomerDAOImpl instance;

    private CustomerDAOImpl() {
    }

    public static synchronized CustomerDAOImpl getInstance() {
        if (instance == null) {
            instance = new CustomerDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (name, phone, email, address) VALUES (?, ?, ?, ?)");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.execute();
            System.out.println("Thêm khách hàng thành công!");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCustomer(int id, Customer customer) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET name = ?, phone = ?, email = ?, address = ? WHERE id = ?");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.setInt(5, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                System.out.println("Khách hàng không tồn tại với ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE id = ?");
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Khách hàng đã được xóa thành công!");
                return true;
            } else {
                System.out.println("Khách hàng không tồn tại với ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa khách hàng: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ResultSet getAllCustomers() {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers");
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching khách hàng: " + e.getMessage());
            return null;
        }
    }

}
