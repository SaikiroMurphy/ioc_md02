package ioc_md02.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import ioc_md02.dao.impl.CustomerDAOImpl;

public class Invoice implements IModel{
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private int customerId;
    private LocalDateTime createdAt;
    private double totalAmount;
    private ArrayList<InvoiceDetail> items;


    public Invoice(int id, int customerId, LocalDateTime createdAt, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
    }


    public Invoice() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public ArrayList<InvoiceDetail> getItems() {
        return items;
    }


    public void setItems(ArrayList<InvoiceDetail> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("|%-5d", id));
        builder.append(String.format("|%-50s", CustomerDAOImpl.getInstance().getCustomerById(customerId).getName()));
        builder.append(String.format("|%-15s", createdAt.format(formatter)));
        builder.append(String.format("|%-12.2f|", totalAmount));
        return builder.toString();
    }


    @Override
    public void inputData(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập ID khách hàng: ");
                this.customerId = Integer.parseInt(scanner.nextLine());

                if(CustomerDAOImpl.getInstance().getCustomerById(customerId) != null) {
                    break;
                }
                System.out.println("Không có khách hàng nào có ID = " + customerId);
            } catch (IllegalArgumentException e) {
                System.out.println("ID nhập vào không hợp lệ");
            }
        }

        this.items = new ArrayList<>();
        while (true) {
            InvoiceDetail item = new InvoiceDetail();
            System.out.print("Bạn muốn thêm sản phẩm vào hóa đơn? (true/false): ");
            boolean add = Boolean.parseBoolean(scanner.nextLine());
            if (add == true) {
                item.inputData(scanner);
                items.add(item);
                continue;
            }
            break;
        }

        for (InvoiceDetail item : items) {
            this.totalAmount += (item.getUnitPrice() * item.getQuantity());
        }
    }
}
