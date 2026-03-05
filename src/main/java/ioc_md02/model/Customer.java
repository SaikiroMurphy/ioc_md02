package ioc_md02.model;

import java.util.Scanner;

public class Customer implements IModel{
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer(int id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Customer() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address="
                + address + "]";
    }

    @Override
    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên khách hàng: ");
            this.name = scanner.nextLine();
            if (!this.name.trim().isEmpty()) {
                break;
            }
            System.out.println("Tên khách hàng không được để trống!");
        }

        System.out.print("Nhập số điện thoại: ");
        this.phone = scanner.nextLine();
        System.out.print("Nhập email: ");
        this.email = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        this.address = scanner.nextLine();
    }

}
