package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.Employee;
import Models.RepairOrder;
import Models.SalesProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeService {

    private PreparedStatement preparedStatement;

    private ResultSet result;

    private DatabaseConnection singleConn;
    public EmployeeService()
    {
        singleConn=DatabaseConnection.getSingleInstance();
    }
    public boolean addEmployee(Employee employee)
    {
        try
        {
            String query="insert into employee(email, name) values ('"+employee.getEmail()+"','"+employee.getName()+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an employee");
            return false;
        }
    }

    public ResultSet getEmployeeDetails() {
        try
        {
            singleConn.setPreparedStatement("select * from employee");
            result = singleConn.ExecutePreparedStatement();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean updateEmployee(String email, String name) {
        try
        {
            String query="update employee set name = '"+name+"' where email = '"+email+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }

    public boolean deleteEmployee(String email) {
        try
        {
            String query="delete from employee where email = '"+email+"'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert a rider");
            return false;
        }
    }
}
