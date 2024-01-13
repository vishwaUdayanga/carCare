package DatabaseLayer;

import java.sql.*;

public class DatabaseConnection {
    private final String URL="jdbc:mysql://localhost:3306/car_care";
    private final String UName="root";
    private final String Password="";
    private static DatabaseConnection instance;
    public Connection con;

    public ResultSet resultSet;

    public PreparedStatement preparedStatement;

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

    public void setPreparedStatement(String sql) {
        try {
            preparedStatement = con.prepareStatement(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setPreparedStatementForLastId(String sql) {
        try {
            preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet ExecutePreparedStatement()
    {
        try
        {
            Statement st=con.createStatement();
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                System.out.println("Result not found");
            } else {
                return resultSet;
            }
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            return resultSet;
        }
        return resultSet;
    }

    public ResultSet ExecutePreparedStatementForLastIndex() {
        try
        {
            Statement st=con.createStatement();
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            return resultSet;
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            System.out.println("error");
            return resultSet;
        }
    }

}
