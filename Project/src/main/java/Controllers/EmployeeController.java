package Controllers;

import Models.Employee;
import ServiceLayer.EmployeeService;
import ServiceLayer.OrderService;

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
}
