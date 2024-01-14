package Controllers;

import Models.SparePart;
import Models.Supplier;
import ServiceLayer.SparePartsService;
import ServiceLayer.SupplierService;

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
}
