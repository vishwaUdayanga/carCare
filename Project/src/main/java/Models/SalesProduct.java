package Models;

public class SalesProduct {
    private int productId;
    private int orderId;
    private Double amount;

    private int qty;

    public SalesProduct(int productId, int orderId, Double amount, int qty) {
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.qty = qty;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
