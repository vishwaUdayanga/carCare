package Controllers;

import Models.Order;
import Models.RepaintOrder;
import Models.RepairOrder;
import ServiceLayer.OrderService;

import java.sql.ResultSet;

public class OrderController {
    RepairOrder repairOrder;

    RepaintOrder repaintOrder;

    Order order;

    OrderService service;

    public OrderController()
    {
        service = new OrderService();
    }

    public RepairOrder addRepairOrder(String email, Double amount, String description, String employeeEmail) {
        repairOrder = new RepairOrder(email, amount, description, employeeEmail);
        return repairOrder;
    }

    public RepaintOrder addRepaintOrder(String email, Double amount, String description, String employeeEmail) {
        repaintOrder = new RepaintOrder(email, amount, description, employeeEmail);
        return repaintOrder;
    }
    public Order addOrder(String email, Double amount)
    {
        order = new Order(email, amount);
        return order;
    }

    public boolean addRepairOrderToDatabase()
    {
        return service.addRepairOrder(repairOrder);
    }

    public boolean addRepaintOrderToDatabase()
    {
        return service.addRepaintOrder(repaintOrder);
    }

    public ResultSet addOrderToDatabase()
    {
        return service.addOrder(order);
    }

    public ResultSet findProductsFromDatabaseById(int id) {
        ResultSet resultSet = service.getProductsById(id);
        return resultSet;
    }
}
