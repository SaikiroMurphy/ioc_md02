package ioc_md02.model;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Invoice implements IModel{
    private int id;
    private int customerId;
    private LocalDateTime createdAt;
    private double totalAmount;


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


    @Override
    public String toString() {
        return "Invoice [id=" + id + ", customerId=" + customerId + ", createdAt=" + createdAt + ", totalAmount="
                + totalAmount + "]";
    }


    @Override
    public void inputData(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputData'");
    }

}
