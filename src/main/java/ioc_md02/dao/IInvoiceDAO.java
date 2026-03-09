package ioc_md02.dao;

import java.sql.ResultSet;
import java.time.LocalDate;

import ioc_md02.model.Invoice;

public interface IInvoiceDAO {
    boolean addInvoice(Invoice invoice);
    ResultSet getAllInvoices();
    Invoice getInvoicesById(int id);
    ResultSet getInvoicesByCustomerName(String customerName);
    ResultSet getInvoicesByDate(LocalDate date);
}
