package Models;

public class Provide {
    private String supplierEmail;
    private int productId;

    private int qty;

    public Provide(String supplierEmail, int productId, int qty) {
        this.supplierEmail = supplierEmail;
        this.productId = productId;
        this.qty = qty;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public int getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
