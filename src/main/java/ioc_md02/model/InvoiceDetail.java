package ioc_md02.model;

import java.util.Scanner;

public class InvoiceDetail implements IModel{
    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public InvoiceDetail(int id, int invoiceId, int productId, int quantity, double unitPrice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%-5d|%-10d|%-15d|%-10d|%-10.2f|", id, invoiceId, productId, quantity, unitPrice));
        return sb.toString();
    }

    @Override
    public void inputData(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputData'");
    }

}
