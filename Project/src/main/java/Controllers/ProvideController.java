package Controllers;

import Models.Provide;
import Models.Supplier;
import ServiceLayer.ProvideService;
import ServiceLayer.SupplierService;

import java.sql.ResultSet;

public class ProvideController {
    Provide provide;

    ProvideService service;

    public ProvideController()
    {
        service = new ProvideService();
    }

    public Provide addProvide(String supplierEmail, int productId, int qty){
        provide = new Provide(supplierEmail, productId, qty);
        return provide;
    }

    public boolean addProvideToDB()
    {
        return service.addProvide(provide);
    }

    public ResultSet findProductById(int id) {
        ResultSet resultSet = service.findProductByIdDB(id);
        return resultSet;
    }

    public ResultSet findSupplierByEmail(String email) {
        ResultSet resultSet = service.findSupplierByEmailIdDB(email);
        return resultSet;
    }

    public boolean updateQty(int id, int qty) {
        return service.updateQtyDB(id, qty);
    }

    public ResultSet findOrders() {
        ResultSet resultSet = service.getOrderDetails();
        return resultSet;
    }
}
