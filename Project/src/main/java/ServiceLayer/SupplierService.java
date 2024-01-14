package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierService {
    private PreparedStatement preparedStatement;

    private ResultSet result;

    private DatabaseConnection singleConn;

    public SupplierService()
    {
        singleConn=DatabaseConnection.getSingleInstance();
    }

    public boolean addSupplier(Supplier supplier)
    {
        try
        {
            String query="insert into supplier(email, name) values ('"+supplier.getEmail()+"','"+supplier.getName()+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an employee");
            return false;
        }
    }

    public ResultSet getSupplierDetails() {
        try
        {
            singleConn.setPreparedStatement("select * from supplier");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean updateSupplier(String email, String name) {
        try
        {
            String query="update supplier set name = '"+name+"' where email = '"+email+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot update a supplier");
            return false;
        }
    }

    public boolean deleteSupplier(String email) {
        try
        {
            String query="delete from supplier where email = '"+email+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot update the supplier");
            return false;
        }
    }
}
