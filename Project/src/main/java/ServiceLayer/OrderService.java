package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.RepairOrder;
public class OrderService {
    private DatabaseConnection singleConn;
    public OrderService()
    {
        singleConn=DatabaseConnection.getSingleInstance();
    }
    public boolean addRepairOrder(RepairOrder repairOrder)
    {
        try
        {
            String query="insert into repair_orders(customer_email, employee_email, description, amount) values ('"+repairOrder.getEmail()+"','"+repairOrder.getEmployeeEmail()+"','"+repairOrder.getDescription()+"','"+repairOrder.getAmount()+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }
}
