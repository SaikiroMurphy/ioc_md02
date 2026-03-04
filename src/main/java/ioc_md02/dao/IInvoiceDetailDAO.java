package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.InvoiceDetail;

public interface IInvoiceDetailDAO {
    void addInvoiceDetail(InvoiceDetail invoiceDetail);
    ResultSet getInvoiceDetailsByInvoiceId(int invoiceId);
}
