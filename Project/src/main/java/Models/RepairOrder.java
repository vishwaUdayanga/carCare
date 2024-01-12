package Models;

public class RepairOrder extends Order {
    private String description;

    private String employeeEmail;

    public RepairOrder(String email, Double amount, String description, String employeeEmail) {
        super(email, amount);
        this.description = description;
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}
