package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.Customer;

public interface ICustomerDAO {
    void addCustomer(Customer customer);
    void updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);
    ResultSet getAllCustomers();
}
