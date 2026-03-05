package ioc_md02.business.impl;

import java.util.Scanner;

import ioc_md02.business.IAdminSerrvice;
import ioc_md02.dao.impl.AdminDAOImpl;
import ioc_md02.model.Admin;

public class AdminServiceImpl implements IAdminSerrvice{

    private static AdminServiceImpl instance;

    private AdminServiceImpl() {}

    public static AdminServiceImpl getInstance() {
        if (instance == null) {
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    @Override
    public void login(Scanner scanner) {
        Admin admin = new Admin();

        do {
            while (true) {
                System.out.print("Enter username: ");
                admin.setUsername(scanner.nextLine());

                if (admin.getUsername().isEmpty()) {
                    System.out.println("Tên đăng nhập không được để trống!");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Enter password: ");
                admin.setPassword(scanner.nextLine());

                if (admin.getPassword().isEmpty()) {
                    System.out.println("Mật khẩu không được để trống!");
                    continue;
                }
                break;
            }

            if(AdminDAOImpl.getInstance().login(admin)) {
                System.out.println("Đăng nhập thành công!");
                System.out.println("=========================================");
                break;
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng!");
                System.out.println("=========================================");
            }

        } while (true);
    }

}
