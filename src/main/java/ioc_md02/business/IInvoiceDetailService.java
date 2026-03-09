package ioc_md02.business;

import java.util.Scanner;

public interface IInvoiceDetailService {
    void addInvoiceDetail(Scanner scanner);
    void getInvoiceDetailsByInvoiceId(Scanner scanner);
}
