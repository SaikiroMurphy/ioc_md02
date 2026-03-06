package ioc_md02.model;

import java.util.Scanner;

import ioc_md02.dao.impl.CustomerDAOImpl;

public class Customer implements IModel{
    private static final String PHONE_REGEX = "^(0|\\+84)\\d{9}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private Integer id;
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
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("|%-5d", id));
        builder.append(String.format("|%-50s", name));
        builder.append(String.format("|%-15s", phone));
        builder.append(String.format("|%-50s", email));
        builder.append(String.format("|%-20s|", address));
        return builder.toString();
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

        while (true) {
            System.out.print("Nhập số điện thoại cá nhân: ");
            this.phone = scanner.nextLine();
            if (this.phone.trim().matches(PHONE_REGEX)) {
                break;
            }
            System.out.println("Số điện thoại không hợp lệ!");
        }

        while (true) {
            System.out.print("Nhập Email: ");
            this.email = scanner.nextLine();
            if (this.email.trim().isEmpty() || !this.email.trim().matches(EMAIL_REGEX)) {
                System.out.println("Email không hợp lệ!");
                continue;
            } else if (CustomerDAOImpl.getInstance().getCustomerByEmail(this.email.trim(), this.id)) {
                System.out.println("Email đã tồn tại!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Nhập địa chỉ thường trú: ");
            this.address = scanner.nextLine();
            if (!this.address.trim().isEmpty()) {
                break;
            }
            System.out.println("Địa chỉ không được để trống!");
        }
    }

}
