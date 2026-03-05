package ioc_md02.business;

import java.sql.ResultSet;
import java.util.Scanner;

public interface IInvoiceDetailService {
    void addInvoiceDetail(Scanner scanner);
    ResultSet getInvoiceDetailsByInvoiceId(Scanner scanner);
}
