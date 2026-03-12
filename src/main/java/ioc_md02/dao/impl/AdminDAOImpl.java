package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

import ioc_md02.dao.IAdminDAO;
import ioc_md02.model.Admin;
import ioc_md02.utils.DBUtil;

public class AdminDAOImpl implements IAdminDAO {
    private static AdminDAOImpl instance;

    private AdminDAOImpl() {
    }

    public static synchronized AdminDAOImpl getInstance() {
        if (instance == null) {
            instance = new AdminDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean login(Admin admin) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admins");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String passwordHash = rs.getString("password");

                if (username.equals(admin.getUsername()) && BCrypt.checkpw(admin.getPassword(), passwordHash)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return false;
    }

    @Override
    public ResultSet getStatisticByDay(int day, int month, int year) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT p.name, SUM(d.quantity), SUM(d.quantity * d.price) " +
                    "FROM invoices i " +
                    "JOIN invoice_details d ON i.id = d.invoice_id " +
                    "JOIN products p ON d.product_id = p.id " +
                    "WHERE i.created_at = ? " +
                    "GROUP BY p.name"
                );

            ps.setDate(1, Date.valueOf(date));
            return ps.executeQuery();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @Override
    public ResultSet getStatisticByMonth(int month, int year) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT p.name, SUM(d.quantity), SUM(d.quantity * d.price) " +
                        "FROM invoices i " +
                        "JOIN invoice_details d ON i.id = d.invoice_id " +
                        "JOIN products p ON d.product_id = p.id " +
                        "WHERE EXTRACT(MONTH FROM i.created_at) = ? " +
                        "AND EXTRACT(YEAR FROM i.created_at) = ? " +
                        "GROUP BY p.name"
                    );

            ps.setDate(1, Date.valueOf(date));
            return ps.executeQuery();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @Override
    public ResultSet getStatisticByYear(int year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatisticByYear'");
    }

}
