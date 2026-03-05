package ioc_md02.model;

import java.util.Scanner;

public class Product implements IModel{
    private int id;
    private String name;
    private String brand;
    private double price;
    private int stock;

    public Product(int id, String name, String brand, double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("|%-5d", id));
        builder.append(String.format("|%-50s", name));
        builder.append(String.format("|%-20s", brand));
        builder.append(String.format("|%-12.2f", price));
        builder.append(String.format("|%-5d|", stock));
        return builder.toString();
    }

    @Override
    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên điện thoại: ");
            this.name = scanner.nextLine();

            if (!this.name.trim().isEmpty()) {
                break;
            }
            System.out.println("Tên điện thoại không được để trống!");
        }

        while (true) {
            System.out.print("Nhập hãng điện thoại: ");
            this.brand = scanner.nextLine();

            if (!this.brand.trim().isEmpty()) {
                break;
            }
            System.out.println("Hãng điện thoại không được để trống!");
        }

        while (true) {
            try{
                System.out.print("Nhập giá tiền điện thoại: ");
                this.price = Double.parseDouble(scanner.nextLine());
                if (!(this.price <= 0)) {
                    break;
                }
                System.out.println("Giá tiền điện thoại phải lớn hơn 0!");

            } catch (IllegalArgumentException e) {
                System.out.println("Số nhập vào không hợp lệ!");
            }
        }

        while (true) {
            try{
                System.out.print("Nhập số lượng tồn kho: ");
                this.stock = Integer.parseInt(scanner.nextLine());
                if (!(this.stock < 0)) {
                    break;
                }
                System.out.println("Số lượng tồn kho không thể nhỏ hơn 0!");

            } catch (IllegalArgumentException e) {
                System.out.println("Số nhập vào không hợp lệ!");
            }
        }
    }
}
