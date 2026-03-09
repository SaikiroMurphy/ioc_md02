package ioc_md02.business;

import java.sql.ResultSet;
import java.util.Scanner;

import ioc_md02.model.Invoice;

public interface IInvoiceService {
    void addInvoice(Scanner scanner);
    void getAllInvoices();
    void getInvoicesByCustomerName(Scanner scanner);
    void getInvoicesByDate(Scanner scanner);
    Invoice getInvoicesById(Scanner scanner);
    void displayInvoices(ResultSet rs);
    void displayInvoices(Invoice invoice);
}
