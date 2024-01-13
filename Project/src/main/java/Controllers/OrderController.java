package Controllers;

import Models.RepaintOrder;
import Models.RepairOrder;
import ServiceLayer.OrderService;

public class OrderController {
    RepairOrder repairOrder;

    RepaintOrder repaintOrder;

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

    public boolean addRepairOrderToDatabase()
    {
        return service.addRepairOrder(repairOrder);
    }

    public boolean addRepaintOrderToDatabase()
    {
        return service.addRepaintOrder(repaintOrder);
    }
}
