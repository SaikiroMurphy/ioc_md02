package ioc_md02.model;

import java.util.Scanner;

public class Admin implements IModel{
    private int id;
    private String username;
    private String password;

    public Admin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    @Override
    public void inputData(Scanner scanner) {
        while(true) {
            System.out.print("Nhập username: ");
            this.username = scanner.nextLine();
            if (!this.username.trim().isEmpty()) {
                break;
            }
            System.out.println("Username không được để trống!");
        }

        while(true) {
            System.out.print("Nhập password: ");
            this.password = scanner.nextLine();
            if (!this.password.trim().isEmpty()) {
                break;
            }
            System.out.println("Password không được để trống!");
        }
    }
}
