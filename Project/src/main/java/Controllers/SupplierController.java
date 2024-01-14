package Controllers;

import Models.Employee;
import Models.Supplier;
import ServiceLayer.EmployeeService;
import ServiceLayer.SupplierService;

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
}
