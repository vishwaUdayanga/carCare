package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.SparePart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SparePartsService {
    private PreparedStatement preparedStatement;

    private ResultSet result;

    private DatabaseConnection singleConn;

    public SparePartsService()
    {
        singleConn=DatabaseConnection.getSingleInstance();
    }

    public boolean addItems(SparePart sparePart)
    {
        try
        {
            String query="insert into spare_parts(name, brand, price) values ('"+sparePart.getName()+"','"+sparePart.getBrand()+"', '"+ sparePart.getPrice() +"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an item");
            return false;
        }
    }
}
