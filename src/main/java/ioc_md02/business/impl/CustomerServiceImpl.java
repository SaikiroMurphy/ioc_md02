package ioc_md02.business.impl;

import java.util.Scanner;

import ioc_md02.business.ICustomerService;

public class CustomerServiceImpl implements ICustomerService{
    private static CustomerServiceImpl instance;

    private CustomerServiceImpl() {}

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    @Override
    public void addCustomer(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCustomer'");
    }

    @Override
    public void updateCustomer(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    @Override
    public void deleteCustomer(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

    @Override
    public void displayAllCustomers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayAllCustomers'");
    }

}
