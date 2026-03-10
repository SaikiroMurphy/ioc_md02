package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import ioc_md02.dao.IInvoiceDAO;
import ioc_md02.model.Invoice;
import ioc_md02.model.InvoiceDetail;
import ioc_md02.utils.DBUtil;

public class InvoiceDAOImpl implements IInvoiceDAO{
    private static InvoiceDAOImpl instance;

    private InvoiceDAOImpl() {
    }

    public static synchronized InvoiceDAOImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO invoices (customer_id, total_amount) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, invoice.getCustomerId());
            stmt.setDouble(2, invoice.getTotalAmount());

            if (stmt.executeUpdate() == 0) {
                System.out.println("Không có dữ liệu nào được thêm vào. Vui lòng kiểm tra lại thông tin.");
                return false;
            }

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int invoiceId = rs.getInt(1);
            for (InvoiceDetail item : invoice.getItems()) {
                ProductDAOImpl.getInstance().updateStockById(item.getProductId(), item.getQuantity());
                item.setInvoiceId(invoiceId);
                InvoiceDetailDAOImpl.getInstance().addInvoiceDetail(item);
            }
            return true;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
            return false;
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
    public ResultSet getInvoicesByCustomerName(String customerName) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT inv.* FROM invoices inv JOIN customers c ON inv.customer_id = c.id WHERE unaccent(c.name) ILIKE unaccent(?)");
            stmt.setString(1, "%" + customerName + "%");
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching hóa đơn: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet getInvoicesByDate(LocalDate date) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM invoices WHERE created_at = ?");
            stmt.setDate(1, Date.valueOf(date));
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching hóa đơn: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Invoice getInvoicesById(int id) {
            try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoices WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Invoice(
                    rs.getInt("id"),
                    rs.getInt("customer_id"),
                    rs.getDate("created_at").toLocalDate(),
                    rs.getDouble("total_amount")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching hóa đơn: " + e.getMessage());
        }
        return null;

    }

}
