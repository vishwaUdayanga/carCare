package Controllers;

import Models.RepairOrder;
import ServiceLayer.OrderService;

public class OrderController {
    RepairOrder repairOrder;

    OrderService service;

    public OrderController()
    {
        service = new OrderService();
    }

    public RepairOrder addRepairOrder(String email, Double amount, String description, String employeeEmail) {
        repairOrder = new RepairOrder(email, amount, description, employeeEmail);
        return repairOrder;
    }

    public boolean addRepairOrderToDatabase()
    {
        return service.addRepairOrder(repairOrder);
    }}
