package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.InvoiceDetail;

public interface IInvoiceDetailDAO {
    boolean addInvoiceDetail(InvoiceDetail invoiceDetail);
    ResultSet getInvoiceDetailsByInvoiceId(int invoiceId);
}
