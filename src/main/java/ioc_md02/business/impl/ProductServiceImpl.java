package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        try {
            if (!products.next()) {
                System.out.println("Không có sản phẩm nào.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayProducts(products);
    }

    @Override
    public void addProduct(Scanner scanner) {
        System.out.println();
        System.out.println("========== THÊM ĐIỆN THOẠI MỚI ==========");

        Product product = new Product();
        product.inputData(scanner);

        if (ProductDAOImpl.getInstance().addProduct(product)) {
            System.out.println("Thêm điện thoại mới thành công!");
        } else {
            System.out.println("Thêm điện thoại mới thất bại!");
        }
    }

    @Override
    public void updateProduct(Scanner scanner) {
        System.out.println();
        System.out.println("========== CẬP NHẬT THÔNG TIN ĐIỆN THOẠI ==========");

        int updateProductId;
        while (true) {
            try {
                System.out.print("Nhập ID điện thoại muốn cập nhật: ");
                updateProductId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập số ID hợp lệ.");
            }
        }

        Product oldProduct = ProductDAOImpl.getInstance().getProductById(updateProductId);

        if (oldProduct == null) {
            System.out.println("Không tìm thấy điện thoại nào với ID phù hợp.");
            return;
        }

        displayProducts(oldProduct);

        Product newProduct = new Product();
        newProduct.inputData(scanner);

        if (ProductDAOImpl.getInstance().updateProduct(updateProductId, newProduct)) {
            System.out.println("Cập nhật thông tin điện thoại thành công!");
        } else {
            System.out.println("Cập nhật thông tin điện thoại thất bại!");
        }
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        System.out.println();
        System.out.println("=============== XÓA ĐIỆN THOẠI ===============");

        int delProductId;
        while (true) {
            try {
                System.out.print("Nhập ID điện thoại muốn xóa: ");
                delProductId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập số ID hợp lệ.");
            }
        }

        if (ProductDAOImpl.getInstance().deleteProduct(delProductId)) {
            System.out.println("Xóa điện thoại thành công!");
        } else {
            System.out.println("Xóa điện thoại thất bại!");
        }
    }

    @Override
    public Product getProductById(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public void getProductsByBrand(Scanner scanner) {
        System.out.println();
        System.out.println("=============== TÌM ĐIỆN THOẠI THEO HÃNG ===============");

        String filterBrand;

        while (true) {
            System.out.print("Nhập tên hãng điện thoại muốn tìm: ");
            filterBrand = scanner.nextLine();
            if (!filterBrand.isEmpty()) {
                break;
            }
            System.out.println("Tên hãng cần tìm không được để trống!");
        }

        ResultSet products = ProductDAOImpl.getInstance().getProductsByBrand(filterBrand);
        try {
            if (!products.next()) {
                System.out.println("Không tìm thấy sản phẩm nào thuộc hãng " + filterBrand);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayProducts(products);
    }

    @Override
    public void getProductsByPriceRange(Scanner scanner) {
        System.out.println();
        System.out.println("=============== TÌM ĐIỆN THOẠI THEO KHOẢNG GIÁ ===============");

        double minPrice, maxPrice;

        while (true) {
            try {
                System.out.print("Nhập mức giá tối thiểu: ");
                minPrice = Double.parseDouble(scanner.nextLine());
                if (minPrice <= 0) {
                    System.out.println("Mức giá tối thiểu phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập lại.");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập mức giá tối đa: ");
                maxPrice = Double.parseDouble(scanner.nextLine());
                if (maxPrice <= minPrice) {
                    System.out.println("Mức giá tối đa phải lớn hơn mức giá tối thiểu!");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập lại.");
            }
        }

        ResultSet products = ProductDAOImpl.getInstance().getProductsByPriceRange(minPrice, maxPrice);
        try {
            if (!products.next()) {
                System.out.println("Không tìm thấy sản phẩm nào nằm trong khoảng giá " + minPrice + " và " + maxPrice);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayProducts(products);
    }

    @Override
    public void getProductsByName(Scanner scanner) {
        System.out.println();
        System.out.println("=============== TÌM ĐIỆN THOẠI THEO TÊN ===============");

        String filterName;

        while (true) {
            System.out.print("Nhập tên điện thoại muốn tìm: ");
            filterName = scanner.nextLine();
            if (!filterName.isEmpty()) {
                break;
            }
            System.out.println("Tên điện thoại cần tìm không được để trống!");
        }

        ResultSet products = ProductDAOImpl.getInstance().getProductsByName(filterName);
        try {
            if (!products.next()) {
                System.out.println("Không tìm thấy sản phẩm nào có tên " + filterName);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayProducts(products);

    }

    @Override
    public void getProductsByStock(Scanner scanner) {
        System.out.println();
        System.out.println("=============== TÌM ĐIỆN THOẠI THEO TỒN KHO ===============");

        System.out.print("Bạn muốn tìm điện thoại còn tồn kho hay đã hết (true - Hết hàng / false - Còn hàng): ");
        boolean stock = Boolean.parseBoolean(scanner.nextLine());

        ResultSet products = ProductDAOImpl.getInstance().getProductsByStock(stock);
        try {
            if (!products.next()) {
                if (stock) {
                    System.out.println("Không có sản phẩm nào trong trạng thái Hết hàng");
                } else {
                    System.out.println("Không có sản phẩm nào trong trạng thái Còn hàng");
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        displayProducts(products);
    }

    @Override
    public void displayProducts(ResultSet rs) {
        Product product;
        System.out.println();
        System.out.println(
                "+------------------------------------- DANH SÁCH ĐIỆN THOẠI -------------------------------------+");
        System.out.println(
                "|ID   |TÊN ĐIỆN THOẠI                                    |HÃNG                |GIÁ TIỀN    |STOCK|");
        System.out.println(
                "+-----+--------------------------------------------------+--------------------+------------+-----+");
        try {
            while (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getInt("stock"));
                System.out.println(product);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi fetching sản phẩm: " + e.getMessage());
        } finally {
            System.out.println(
                    "+------------------------------------------------------------------------------------------------+");
        }

    }

    @Override
    public void displayProducts(Product product) {
        System.out.println(
                "+------------------------------------- DANH SÁCH ĐIỆN THOẠI -------------------------------------+");
        System.out.println(
                "|ID   |TÊN ĐIỆN THOẠI                                    |HÃNG                |GIÁ TIỀN    |STOCK|");
        System.out.println(
                "+-----+--------------------------------------------------+--------------------+------------+-----+");
        System.out.println(product);
        System.out.println(
                "+------------------------------------------------------------------------------------------------+");

    }

}
