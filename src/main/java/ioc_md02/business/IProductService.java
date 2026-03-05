package ioc_md02.business;

import java.sql.ResultSet;
import java.util.Scanner;

import ioc_md02.model.Product;

public interface IProductService {
    void getAllProducts();
    void addProduct(Scanner scanner);
    void updateProduct(Scanner scanner);
    void deleteProduct(Scanner scanner);
    Product getProductById(Scanner scanner);
    void getProductsByBrand(Scanner scanner);
    void getProductsByPriceRange(Scanner scanner);
    void getProductsByName(Scanner scanner);
    void getProductsByStock(Scanner scanner);
    void displayProducts(ResultSet rs);
    void displayProducts(Product product);
}
