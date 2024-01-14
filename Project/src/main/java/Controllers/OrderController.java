package Controllers;

import Models.Order;
import Models.RepaintOrder;
import Models.RepairOrder;
import Models.SalesProduct;
import ServiceLayer.OrderService;

import java.sql.ResultSet;

public class OrderController {
    RepairOrder repairOrder;

    RepaintOrder repaintOrder;

    Order order;

    SalesProduct salesProduct;

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

    public SalesProduct addSalesProduct(int productId, int orderId, Double amount, int qty) {
        salesProduct = new SalesProduct(productId, orderId, amount, qty);
        return salesProduct;
    }

    public boolean addRepairOrderToDatabase()
    {
        return service.addRepairOrder(repairOrder);
    }

    public boolean addRepaintOrderToDatabase()
    {
        return service.addRepaintOrder(repaintOrder);
    }

    public boolean addSalesProductToDatabase()
    {
        return service.addSalesProduct(salesProduct);
    }

    public boolean reduceQty(int productId, int qty) {
        return service.reduceQtyDB(productId, qty);
    }

    public ResultSet addOrderToDatabase()
    {
        return service.addOrder(order);
    }

    public ResultSet findProductsFromDatabaseById(int id) {
        ResultSet resultSet = service.getProductsById(id);
        return resultSet;
    }

    public ResultSet findRepairOrders() {
        ResultSet resultSet = service.getRepairOrders();
        return resultSet;
    }

    public boolean deleteRepairOrder(String id)
    {
        return service.deleteRepairOrder(id);
    }

    public boolean updateRepairOrderStatus(String id)
    {
        return service.updateRepairOrderStatus(id);
    }

    public ResultSet findRepaintOrder() {
        ResultSet resultSet = service.getRepaintOrders();
        return resultSet;
    }

    public boolean deleteRepaintOrder(String id)
    {
        return service.deleteRepaintOrder(id);
    }

    public boolean updateRepaintOrderStatus(String id)
    {
        return service.updateRepaintOrderStatus(id);
    }
}
