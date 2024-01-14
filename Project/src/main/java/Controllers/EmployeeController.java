package Controllers;

import Models.Employee;
import ServiceLayer.EmployeeService;
import ServiceLayer.OrderService;

import java.sql.ResultSet;

public class EmployeeController {
    Employee employee;
    EmployeeService service;

    public EmployeeController()
    {
        service = new EmployeeService();
    }
    public Employee addEmployee(String email, String name){
        employee = new Employee(email,name);
        return employee;
    }

    public boolean addEmployeeToDatabase()
    {
        return service.addEmployee(employee);
    }

    public boolean updateEmployeeDB(String email, String name)
    {
        return service.updateEmployee(email, name);
    }

    public boolean deleteEmployeeDB(String email)
    {
        return service.deleteEmployee(email);
    }

    public ResultSet findEmployees() {
        ResultSet resultSet = service.getEmployeeDetails();
        return resultSet;
    }
}
