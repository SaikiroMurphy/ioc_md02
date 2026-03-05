package ioc_md02.business;

import java.util.Scanner;

public interface ICustomerService {
    void addCustomer(Scanner scanner);
    void updateCustomer(Scanner scanner);
    void deleteCustomer(Scanner scanner);
    void displayAllCustomers();
}
