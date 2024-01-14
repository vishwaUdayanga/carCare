package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.Order;
import Models.RepaintOrder;
import Models.RepairOrder;
import Models.SalesProduct;

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

    public boolean addSalesProduct(SalesProduct salesProduct) {
        try
        {
            String query="insert into sales_products(product_id, order_id, qty, amount) values ('"+salesProduct.getProductId()+"','"+salesProduct.getOrderId()+"','"+salesProduct.getQty()+"','"+salesProduct.getAmount()+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }

    public boolean reduceQtyDB(int productId, int qty) {
        try
        {
            String query="update spare_parts set qty = qty - "+qty+" where id = "+productId;
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

    public ResultSet getRepairOrders() {
        try
        {
            singleConn.setPreparedStatement("select * from repair_orders");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean deleteRepairOrder(String id) {
        try
        {
            String query="delete from repair_orders where order_id = '"+id+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot delete the repair order");
            return false;
        }
    }

    public boolean updateRepairOrderStatus(String id) {
        try
        {
            String query="update repair_orders set status = 1 where order_id = '"+id+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot update the repair order");
            return false;
        }
    }

    public ResultSet getRepaintOrders() {
        try
        {
            singleConn.setPreparedStatement("select * from repaint_orders");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean deleteRepaintOrder(String id) {
        try
        {
            String query="delete from repaint_orders where order_id = '"+id+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot delete the repaint order");
            return false;
        }
    }

    public boolean updateRepaintOrderStatus(String id) {
        try
        {
            String query="update repaint_orders set status = 1 where order_id = '"+id+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot update the repaint order");
            return false;
        }
    }
}
