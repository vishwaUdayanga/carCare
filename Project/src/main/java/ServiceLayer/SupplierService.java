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
}
