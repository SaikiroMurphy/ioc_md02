package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ioc_md02.dao.IAdminDAO;
import ioc_md02.model.Admin;
import ioc_md02.utils.DBUtil;

public class AdminDAOImpl implements IAdminDAO {

    @Override
    public void login(Admin admin) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Đăng nhập thành công cho admin: " + admin.getUsername());
            } else {
                System.out.println("Đăng nhập thất bại cho admin: " + admin.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
