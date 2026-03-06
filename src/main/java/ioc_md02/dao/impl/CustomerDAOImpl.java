package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
            if (stmt.executeUpdate() == 0) {
                System.out.println("Không có dữ liệu nào được thêm vào. Vui lòng kiểm tra lại thông tin.");
                return false;
            }
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
            if (stmt.executeUpdate() == 0) {
                System.out.println("Không có dữ liệu nào được cập nhật. Vui lòng kiểm tra lại thông tin.");
                return false;
            }
            return true;
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
            if (stmt.executeUpdate() == 0) {
                System.out.println("Không có dữ liệu nào được xóa đi. Vui lòng kiểm tra lại thông tin.");
                return false;
            }
            return true;
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

	@Override
	public Customer getCustomerById(int id) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching khách hàng: " + e.getMessage());
        }
        return null;
	}

    @Override
    public boolean getCustomerByEmail(String email, Integer id){
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT EXISTS (SELECT 1 FROM customers WHERE email = ? AND (? IS NULL OR id <> ?))");
            ps.setString(1, email);
            if (id == null) {
                ps.setNull(2, Types.INTEGER);
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(2, id);
                ps.setInt(3, id);
            }

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi fetching khách hàng: " + e.getMessage());
        }

        return true;
    }
}
