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

    public ResultSet getSparePartsDetails() {
        try
        {
            singleConn.setPreparedStatement("select * from spare_parts");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean updateSparePartDB(int id, String name, String brand, Double price)
    {
        try
        {
            String query="update spare_parts set name = '"+name+"', brand = '"+brand+"', price = "+price+" where id = "+id;
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }

    public boolean deleteSparePartDB(int id) {
        try
        {
            String query="delete from spare_parts where id = "+id;
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot update the spare part");
            return false;
        }
    }
}
