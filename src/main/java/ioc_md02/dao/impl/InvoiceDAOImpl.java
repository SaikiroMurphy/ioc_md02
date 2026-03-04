package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ioc_md02.dao.IInvoiceDAO;
import ioc_md02.model.Invoice;
import ioc_md02.utils.DBUtil;

public class InvoiceDAOImpl implements IInvoiceDAO{

    @Override
    public void addInvoice(Invoice invoice) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO invoices (customer_id, created_at, total_amount) VALUES (?, ?, ?)");
            stmt.setInt(1, invoice.getCustomerId());
            stmt.setString(2, invoice.getCreatedAt().toString());
            stmt.setDouble(3, invoice.getTotalAmount());
            stmt.execute();
            System.out.println("Hóa đơn đã được thêm thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
        }

    }

    @Override
    public ResultSet getAllInvoices() {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM invoices");
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching hóa đơn: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet getInvoicesByCustomerId(int customerId) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM invoices WHERE customer_id = ?");
            stmt.setInt(1, customerId);
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching hóa đơn: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet getInvoicesByDate(String date) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM invoices WHERE DATE(created_at) = ?");
            stmt.setString(1, date);
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching hóa đơn: " + e.getMessage());
            return null;
        }
    }

}
