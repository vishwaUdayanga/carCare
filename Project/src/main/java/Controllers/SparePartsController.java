package Controllers;

import Models.SparePart;
import Models.Supplier;
import ServiceLayer.SparePartsService;
import ServiceLayer.SupplierService;

import java.sql.ResultSet;

public class SparePartsController {
    SparePart sparePart;
    SparePartsService service;

    public SparePartsController()
    {
        service = new SparePartsService();
    }

    public SparePart addItem(String name, String brand, Double price){
        sparePart = new SparePart(name, brand, price);
        return sparePart;
    }

    public boolean addSparePartsToDB()
    {
        return service.addItems(sparePart);
    }

    public ResultSet findSpareParts() {
        ResultSet resultSet = service.getSparePartsDetails();
        return resultSet;
    }

    public boolean updateSparePart(int id, String name, String brand, Double price) {
        return service.updateSparePartDB(id, name, brand, price);
    }

    public boolean deleteSparePart(int id)
    {
        return service.deleteSparePartDB(id);
    }
}
