package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import ioc_md02.dao.IAdminDAO;
import ioc_md02.model.Admin;
import ioc_md02.utils.DBUtil;

public class AdminDAOImpl implements IAdminDAO {
    private static AdminDAOImpl instance;

    private AdminDAOImpl() {
    }

    public static synchronized AdminDAOImpl getInstance() {
        if (instance == null) {
            instance = new AdminDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean login(Admin admin) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admins");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String passwordHash = rs.getString("password");

                if (username.equals(admin.getUsername()) && BCrypt.checkpw(admin.getPassword(), passwordHash)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return false;
    }

}
