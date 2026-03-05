package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ioc_md02.dao.IInvoiceDetailDAO;
import ioc_md02.utils.DBUtil;
import ioc_md02.model.InvoiceDetail;

public class InvoiceDetailDAOImpl implements IInvoiceDetailDAO{
    private static InvoiceDetailDAOImpl instance;

    private InvoiceDetailDAOImpl() {
    }

    public static synchronized InvoiceDetailDAOImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceDetailDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean addInvoiceDetail(InvoiceDetail invoiceDetail) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invoice_details (invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)");
            ps.setInt(1, invoiceDetail.getInvoiceId());
            ps.setInt(2, invoiceDetail.getProductId());
            ps.setInt(3, invoiceDetail.getQuantity());
            ps.setDouble(4, invoiceDetail.getUnitPrice());
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResultSet getInvoiceDetailsByInvoiceId(int invoiceId) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice_details WHERE invoice_id = ?");
            ps.setInt(1, invoiceId);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching chi tiết hóa đơn: " + e.getMessage());
            return null;
        }
    }

}
