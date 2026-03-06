package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ioc_md02.dao.IProductDAO;
import ioc_md02.model.Product;
import ioc_md02.utils.DBUtil;

public class ProductDAOImpl implements IProductDAO{
    private static ProductDAOImpl instance;

    private ProductDAOImpl() {
    }

    public static synchronized ProductDAOImpl getInstance() {
        if (instance == null) {
            instance = new ProductDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean addProduct(Product product) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO products (name, brand, price, stock) VALUES (?, ?, ?, ?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            if (ps.executeUpdate() == 0){
                System.out.println("Không có dữ liệu nào được thêm vào. Vui lòng kiểm tra lại thông tin.");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProduct(int id, Product product) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE products SET name = ?, brand = ?, price = ?, stock = ? WHERE id = ?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, id);
            if (ps.executeUpdate() == 0) {
                System.out.println("Không có dữ liệu nào được cập nhật. Vui lòng kiểm tra lại thông tin.");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                System.out.println("Không có dữ liệu nào được xóa đi. Vui lòng kiểm tra lại thông tin.");

                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Product getProductById(int id) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getAllProducts() {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getProductsByBrand(String brand) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE brand ILIKE ?");
            ps.setString(1, brand);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm theo thương hiệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getProductsByPriceRange(double minPrice, double maxPrice) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE price BETWEEN ? AND ?");
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm theo khoảng giá: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getProductsByName(String name) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE name ILIKE ?");
            ps.setString(1, "%" + name + "%");
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm theo tên: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getProductsByStock(boolean stock) {
        try(Connection conn = DBUtil.getConnection()) {
            String sql;
            if (stock) {
                sql = "SELECT * FROM products WHERE stock = 0";
            } else {
                sql = "SELECT * FROM products WHERE stock > 0";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm theo số lượng tồn kho: " + e.getMessage());
        }
        return null;
    }

}
