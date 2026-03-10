package ioc_md02.model;

import java.util.Scanner;

import ioc_md02.business.impl.ProductServiceImpl;
import ioc_md02.dao.impl.ProductDAOImpl;

public class InvoiceDetail implements IModel{
    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public InvoiceDetail(int id, int invoiceId, int productId, int quantity, double unitPrice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%-50s|%-12.2f|%-10d|%-15.2f|", ProductDAOImpl.getInstance().getProductById(productId).getName(), unitPrice, quantity, (quantity*unitPrice)));
        return sb.toString();
    }

    @Override
    public void inputData(Scanner scanner) {
        ProductServiceImpl.getInstance().getAllProducts();

        while (true) {
            try {
                System.out.print("Nhập ID điện thoại muốn thêm: ");
                this.productId = Integer.parseInt(scanner.nextLine());

                Product product = ProductDAOImpl.getInstance().getProductById(this.productId);
                if(product == null) {
                    System.out.println("Không có điện thoại nào có ID = " + this.productId);
                    continue;

                } else if(product.getStock() == 0) {
                    System.out.println("Điện thoại hết hàng tồn kho.");
                    continue;
                }

                this.unitPrice = product.getPrice();
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("Số nhập vào không hợp lệ!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập số lượng muốn mua: ");
                this.quantity = Integer.parseInt(scanner.nextLine());

                if (this.quantity <= 0) {
                    System.out.println("Số lượng điện thoại muốn thêm phải lớn hơn 0!");
                    continue;
                } else {
                    int stock = ProductDAOImpl.getInstance().getStockById(this.productId);
                    if (stock == -1) {
                        System.out.println("Lấy thông tin tồn kho sản phẩm thất bại.");
                        continue;
                    } else if(stock < this.quantity) {
                        System.out.println("Số lượng sản phẩm tồn kho không đủ: " + stock);
                        continue;
                    }
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Số nhập vào không hợp lệ!");
            }
        }
    }

}
