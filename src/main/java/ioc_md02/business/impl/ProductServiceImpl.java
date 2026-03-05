package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.util.Scanner;

import ioc_md02.business.IProductService;
import ioc_md02.dao.impl.ProductDAOImpl;
import ioc_md02.model.Product;

public class ProductServiceImpl implements IProductService{
    private static ProductServiceImpl instance;

    private ProductServiceImpl() {}

    public static ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public void getAllProducts() {
        ResultSet products = ProductDAOImpl.getInstance().getAllProducts();
        Product product;

        System.out.println("+-------------------------------------- DANH SÁCH SẢN PHẨM --------------------------------------+");
        System.out.println("|ID   |TÊN ĐIỆN THOẠI                                    |HÃNG                |GIÁ TIỀN    |STOCK|");
        System.out.println("+-----+--------------------------------------------------+--------------------+------------+-----+");
        try {
            while (products.next()) {
                product = new Product(
                    products.getInt("id"),
                    products.getString("name"),
                    products.getString("brand"),
                    products.getDouble("price"),
                    products.getInt("stock")
                );
                System.out.println(product);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("+------------------------------------------------------------------------------------------------+");
        }
    }

    @Override
    public void addProduct(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }

    @Override
    public void updateProduct(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public Product getProductById(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public ResultSet getProductsByBrand(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByBrand'");
    }

    @Override
    public ResultSet getProductsByPriceRange(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByPriceRange'");
    }

    @Override
    public ResultSet getProductsByName(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByName'");
    }

    @Override
    public ResultSet getProductsByStock(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByStock'");
    }

}
