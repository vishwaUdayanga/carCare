package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.Employee;
import Models.RepairOrder;

public class EmployeeService {

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
}
