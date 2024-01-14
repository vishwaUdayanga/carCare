package Controllers;

import Models.Employee;
import Models.Supplier;
import ServiceLayer.EmployeeService;
import ServiceLayer.SupplierService;

import java.sql.ResultSet;

public class SupplierController {
    Supplier supplier;
    SupplierService service;

    public SupplierController()
    {
        service = new SupplierService();
    }

    public Supplier addSupplier(String email, String name){
        supplier = new Supplier(email,name);
        return supplier;
    }

    public boolean addSupplerToDatabase()
    {
        return service.addSupplier(supplier);
    }

    public ResultSet findSuppliers() {
        ResultSet resultSet = service.getSupplierDetails();
        return resultSet;
    }

    public boolean updateSupplierDB(String email, String name)
    {
        return service.updateSupplier(email, name);
    }

    public boolean deleteSupplierDB(String email)
    {
        return service.deleteSupplier(email);
    }
}
