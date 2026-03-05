package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.util.Scanner;

import ioc_md02.business.IInvoiceDetailService;

public class InvoiceDetailServiceImpl implements IInvoiceDetailService{
    private static InvoiceDetailServiceImpl instance;

    private InvoiceDetailServiceImpl() {}

    public static InvoiceDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceDetailServiceImpl();
        }
        return instance;
    }

    @Override
    public void addInvoiceDetail(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addInvoiceDetail'");
    }

    @Override
    public ResultSet getInvoiceDetailsByInvoiceId(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvoiceDetailsByInvoiceId'");
    }

}
