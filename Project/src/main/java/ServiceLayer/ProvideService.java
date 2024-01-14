package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.Provide;
import Models.RepairOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProvideService {
    private DatabaseConnection singleConn;

    private PreparedStatement preparedStatement;

    private ResultSet result;

    public ProvideService()
    {
        singleConn=DatabaseConnection.getSingleInstance();
    }

    public boolean addProvide(Provide provide)
    {
        try
        {
            String query="insert into provide(supplier_email, product_id, qty) values ('"+provide.getSupplierEmail()+"','"+provide.getProductId()+"','"+provide.getQty()+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }

    public ResultSet findProductByIdDB(int id) {
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

    public ResultSet findSupplierByEmailIdDB(String email) {
        try
        {
            singleConn.setPreparedStatement("select * from supplier where email=?");
            singleConn.preparedStatement.setString(1, email);
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean updateQtyDB(int id, int qty)
    {
        try
        {
            String query="update spare_parts set qty = qty + "+qty+" where id = "+id;
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }

    public ResultSet getOrderDetails() {
        try
        {
            singleConn.setPreparedStatement("select * from provide");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
