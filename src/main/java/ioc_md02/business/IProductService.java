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
    ResultSet getProductsByBrand(Scanner scanner);
    ResultSet getProductsByPriceRange(Scanner scanner);
    ResultSet getProductsByName(Scanner scanner);
    ResultSet getProductsByStock(Scanner scanner);
}
