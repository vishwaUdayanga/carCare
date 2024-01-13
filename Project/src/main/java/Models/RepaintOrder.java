package Models;

public class RepaintOrder extends Order{
    private String description;
    private String employeeEmail;

    public RepaintOrder(String email, Double amount, String description, String employeeEmail) {
        super(email, amount);
        this.description = description;
        this.employeeEmail = employeeEmail;
    }

    public String getDescription() {
        return description;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}
