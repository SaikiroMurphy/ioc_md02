package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.util.Scanner;

import ioc_md02.business.IInvoiceService;

public class InvoiceServiceImpl implements IInvoiceService{
    private static InvoiceServiceImpl instance;

    private InvoiceServiceImpl() {}

    public static InvoiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceServiceImpl();
        }
        return instance;
    }

    @Override
    public void addInvoice(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addInvoice'");
    }

    @Override
    public ResultSet getAllInvoices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllInvoices'");
    }

    @Override
    public ResultSet getInvoicesByCustomerId(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvoicesByCustomerId'");
    }

    @Override
    public ResultSet getInvoicesByDate(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvoicesByDate'");
    }

}
