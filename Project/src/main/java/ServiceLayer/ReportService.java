package ServiceLayer;

import DatabaseLayer.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportService {

    private DatabaseConnection singleConn;

    private PreparedStatement preparedStatement;

    private ResultSet result;

    public ReportService()
    {
        singleConn=DatabaseConnection.getSingleInstance();
    }

    public ResultSet findOrderDetailsDB() {
        try
        {
            singleConn.setPreparedStatement("SELECT * FROM `orders` WHERE Timestampdiff(month,date,CURRENT_DATE());");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public ResultSet findRepaintOrderDetailsDB() {
        try
        {
            singleConn.setPreparedStatement("SELECT * FROM `repaint_orders` WHERE Timestampdiff(month,date,CURRENT_DATE());");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public ResultSet findRepairOrderDetailsDB() {
        try
        {
            singleConn.setPreparedStatement("SELECT * FROM `repair_orders` WHERE Timestampdiff(month,date,CURRENT_DATE());");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public ResultSet findAllOrderDetailsDB() {
        try
        {
            singleConn.setPreparedStatement("select sum(amount) as count from orders where Timestampdiff(month,date,CURRENT_DATE());");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public ResultSet findAllRepairOrderDetailsDB() {
        try
        {
            singleConn.setPreparedStatement("select sum(amount) as count from repair_orders where Timestampdiff(month,date,CURRENT_DATE());");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public ResultSet findAllRepaintOrderDetailsDB() {
        try
        {
            singleConn.setPreparedStatement("select sum(amount) as count from repaint_orders where Timestampdiff(month,date,CURRENT_DATE());");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
