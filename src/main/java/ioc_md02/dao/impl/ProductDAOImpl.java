package ioc_md02.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ioc_md02.dao.IProductDAO;
import ioc_md02.model.Product;
import ioc_md02.utils.DBUtil;

public class ProductDAOImpl implements IProductDAO{

    @Override
    public void addProduct(Product product) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO products (name, brand, price, stock) VALUES (?, ?, ?, ?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.execute();
            System.out.println("Thêm điện thoại thành công!");
        } catch (Exception e) {
            System.out.println("Thêm sản phẩm thất bại: " + e.getMessage());
        }
    }

    @Override
    public void updateProduct(int id, Product product) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE products SET name = ?, brand = ?, price = ?, stock = ? WHERE id = ?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, id);
            ps.execute();
            System.out.println("Sản phẩm đã được cập nhật thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int id) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            System.out.println("Sản phẩm đã được xóa thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE brand = ?");
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
    public ResultSet getProductsByStock(int stock) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE stock = ?");
            ps.setInt(1, stock);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm theo số lượng tồn kho: " + e.getMessage());
        }
        return null;
    }

}
