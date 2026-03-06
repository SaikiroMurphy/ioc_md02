package ioc_md02.business;

import java.sql.ResultSet;
import java.util.Scanner;

import ioc_md02.model.Customer;

public interface ICustomerService {
    void addCustomer(Scanner scanner);
    void updateCustomer(Scanner scanner);
    void deleteCustomer(Scanner scanner);
    void displayAllCustomers();
    void displayCustomers(ResultSet rs);
    void displayCustomers(Customer customer);
}
