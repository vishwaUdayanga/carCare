package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.RepaintOrder;
import Models.RepairOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderService {
    private DatabaseConnection singleConn;

    private PreparedStatement preparedStatement;

    private ResultSet result;
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

    public boolean addRepaintOrder(RepaintOrder repaintOrder)
    {
        try
        {
            String query="insert into repaint_orders(customer_email, employee_email, description, amount) values ('"+repaintOrder.getEmail()+"','"+repaintOrder.getEmployeeEmail()+"','"+repaintOrder.getDescription()+"','"+repaintOrder.getAmount()+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }

    public PreparedStatement getPreparedStatement(int id) {
        try
        {
            preparedStatement = singleConn.con.prepareStatement("select * from spare_parts where id = ?");
            preparedStatement.setString(1, String.valueOf(id));
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
        return preparedStatement;
    }
}
