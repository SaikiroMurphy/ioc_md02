package ioc_md02.presentation;

import java.util.Scanner;

import ioc_md02.business.impl.ProductServiceImpl;

public class ProductView {
    private static ProductView instance;

    private ProductView() {}

    public static ProductView getInstance() {
        if (instance == null) {
            instance = new ProductView();
        }
        return instance;
    }

    public void showProductMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("========== QUẢN LÝ ĐIỆN THOẠI ==========");
            System.out.println("1. Xem danh sách điện thoại");
            System.out.println("2. Thêm điện thoại mới");
            System.out.println("3. Cập nhật thông tin điện thoại");
            System.out.println("4. Xóa điện thoại");
            System.out.println("5. Tìm điện thoại theo hãng");
            System.out.println("6. Tìm điện thoại theo khoảng giá");
            System.out.println("7. Tìm điện thoại theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("========================================");

            int choice = -1;
            try {
                System.out.print("Vui lòng chọn (1-8): ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    ProductServiceImpl.getInstance().getAllProducts();
                    break;
                case 2:
                    ProductServiceImpl.getInstance().addProduct(scanner);
                    break;
                case 3:
                    ProductServiceImpl.getInstance().updateProduct(scanner);
                    break;
                case 4:
                    ProductServiceImpl.getInstance().deleteProduct(scanner);
                    break;
                case 5:
                    ProductServiceImpl.getInstance().getProductsByBrand(scanner);
                    break;
                case 6:
                    ProductServiceImpl.getInstance().getProductsByPriceRange(scanner);
                    break;
                case 7:
                    ProductServiceImpl.getInstance().getProductsByStock(scanner);;
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
