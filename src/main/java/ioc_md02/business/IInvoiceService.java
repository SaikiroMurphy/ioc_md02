package ioc_md02.business;

import java.sql.ResultSet;
import java.util.Scanner;

public interface IInvoiceService {
    void addInvoice(Scanner scanner);
    ResultSet getAllInvoices();
    ResultSet getInvoicesByCustomerId(Scanner scanner);
    ResultSet getInvoicesByDate(Scanner scanner);
}
