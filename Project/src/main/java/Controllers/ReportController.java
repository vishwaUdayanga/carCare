package Controllers;

import ServiceLayer.ProvideService;
import ServiceLayer.ReportService;

import java.sql.ResultSet;

public class ReportController {

    ReportService service;
    public ReportController()
    {
        service = new ReportService();
    }

    public ResultSet findOrderDetails() {
        ResultSet resultSet = service.findOrderDetailsDB();
        return resultSet;
    }

    public ResultSet findRepaintOrderDetails() {
        ResultSet resultSet = service.findRepaintOrderDetailsDB();
        return resultSet;
    }

    public ResultSet findRepairOrderDetails() {
        ResultSet resultSet = service.findRepairOrderDetailsDB();
        return resultSet;
    }

    public ResultSet findAllOrderDetails() {
        ResultSet resultSet = service.findAllOrderDetailsDB();
        return resultSet;
    }

    public ResultSet findAllRepairOrderDetails() {
        ResultSet resultSet = service.findAllRepairOrderDetailsDB();
        return resultSet;
    }

    public ResultSet findAllRepaintOrderDetails() {
        ResultSet resultSet = service.findAllRepaintOrderDetailsDB();
        return resultSet;
    }
}
