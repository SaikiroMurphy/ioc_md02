package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.Invoice;

public interface IInvoiceDAO {
    boolean addInvoice(Invoice invoice);
    ResultSet getAllInvoices();
    ResultSet getInvoicesByCustomerId(int customerId);
    ResultSet getInvoicesByDate(String date);
}
