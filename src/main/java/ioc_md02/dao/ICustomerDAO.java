package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.Customer;

public interface ICustomerDAO {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(int id, Customer customer);
    boolean deleteCustomer(int id);
    Customer getCustomerById(int id);
    boolean getCustomerByEmail(String email, Integer id);
    ResultSet getAllCustomers();
}
