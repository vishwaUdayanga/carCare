package Models;

public class RepaintOrder extends Order{
    private boolean status;
    private String description;

    public RepaintOrder(String email, Double amount, boolean status, String description) {
        super(email, amount);
        this.status = status;
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
