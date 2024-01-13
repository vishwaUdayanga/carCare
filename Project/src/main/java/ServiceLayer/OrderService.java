package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.Order;
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

    public ResultSet addOrder(Order order) {
        try
        {
            singleConn.setPreparedStatementForLastId("insert into orders(customer_email, amount) values ('"+order.getEmail()+"','"+order.getAmount()+"')");
            result = singleConn.ExecutePreparedStatementForLastIndex();
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an Order");
            return result;
        }
    }

    public ResultSet getProductsById(int id) {
        try
        {
            System.out.println(id);
            singleConn.setPreparedStatement("select * from spare_parts where id=?");
            singleConn.preparedStatement.setInt(1, id);
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
