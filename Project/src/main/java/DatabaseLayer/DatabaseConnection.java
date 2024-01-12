package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private final String URL="jdbc:mysql://localhost:3306/car_care";
    private final String UName="root";
    private final String Password="";
    private static DatabaseConnection instance;
    private Connection con;

    private DatabaseConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(URL,UName,Password);
            System.out.println("Database Connection Sucess");
        }catch (ClassNotFoundException ex)
        {
            System.out.println("Driver Class Error "+ex.getMessage());
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
        }
    }
    public static DatabaseConnection getSingleInstance()//Singleton Design Pattern
    {
        try {
            if (instance == null) {
                instance = new DatabaseConnection();
            } else if (instance.con.isClosed()) {
                instance = new DatabaseConnection();
            }else{
                return instance;
            }
            return instance;
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
            return null;
        }
    }
    public boolean ExecuteQuery(String sqlQ)
    {
        try
        {
            Statement st=con.createStatement();
            int result=st.executeUpdate(sqlQ);
            return result>0;
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            return false;
        }
    }
}
