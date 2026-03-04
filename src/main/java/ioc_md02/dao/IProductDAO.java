package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.Product;

public interface IProductDAO {
    void addProduct(Product product);
    void updateProduct(int id, Product product);
    void deleteProduct(int id);
    Product getProductById(int id);
    ResultSet getAllProducts();
    ResultSet getProductsByBrand(String brand);
    ResultSet getProductsByPriceRange(double minPrice, double maxPrice);
    ResultSet getProductsByName(String name);
    ResultSet getProductsByStock(int stock);
}
