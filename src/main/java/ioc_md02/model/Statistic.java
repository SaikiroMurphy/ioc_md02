package ioc_md02.model;

public class Statistic {
    private String productName;
    private int totalQuantity;
    private double revenue;

    public Statistic(String productName, int totalQuantity, double revenue) {
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.revenue = revenue;
    }
    public Statistic() {
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    public double getRevenue() {
        return revenue;
    }
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%-50s", productName));
        sb.append(String.format("|%-5d", totalQuantity));
        sb.append(String.format("|%-15.2f|", revenue));

        return sb.toString();
    }


}
