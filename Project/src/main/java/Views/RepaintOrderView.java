package Views;

import javax.swing.*;
import java.awt.*;

public class RepaintOrderView extends JFrame {
    private JPanel backPane;
    private JLabel companyName;
    private JButton orderViewButton;
    private JButton repairOrdersButton;
    private JButton repaintOrdersButton;
    private JButton manageInventoryButton;
    private JButton sellersButton;
    private JButton employeesButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addButton;
    private JButton deleteButton;
    private JTable productCodeTable;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton payInvoiceButton;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;

    public static void main(String[] args) {
        RepaintOrderView repaintOrderView = new RepaintOrderView();
        repaintOrderView.setContentPane(repaintOrderView.backPane);
        repaintOrderView.setTitle("Order View");
        repaintOrderView.setSize(1200, 600);
        repaintOrderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaintOrderView.setVisible(true);
    }


    private void createUIComponents() {
        // Company icon
        ImageIcon icon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\mainIcon.png");
        Image iconImage = icon.getImage();
        Image modified = iconImage.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(modified);
        companyName = new JLabel( icon);

        // Order view icon
        ImageIcon orderViewIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\orderView.png");
        Image orderViewIconImage = orderViewIcon.getImage();
        Image modifiedOrderViewIcon = orderViewIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        orderViewIcon = new ImageIcon(modifiedOrderViewIcon);
        orderViewButton = new JButton( orderViewIcon);

        // Repair view icon
        ImageIcon repairIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\inventory.png");
        Image repairIconImage = repairIcon.getImage();
        Image modifiedRepairIcon = repairIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        repairIcon = new ImageIcon(modifiedRepairIcon);
        repairOrdersButton = new JButton(repairIcon);

        // Repaint view icon
        ImageIcon repaintIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\repaint.png");
        Image repaintIconImage = repaintIcon.getImage();
        Image modifiedRepaintIcon = repaintIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        repaintIcon = new ImageIcon(modifiedRepaintIcon);
        repaintOrdersButton = new JButton(repaintIcon);

        // Inventory icon
        ImageIcon inventoryIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\inventory.png");
        Image inventoryIconImage = inventoryIcon.getImage();
        Image modifiedInventoryIcon = inventoryIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        inventoryIcon = new ImageIcon(modifiedInventoryIcon);
        manageInventoryButton = new JButton(inventoryIcon);

        // Sellers icon
        ImageIcon sellersIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\sellers.png");
        Image sellersIconImage = sellersIcon.getImage();
        Image modifiedSellersIcon = sellersIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        sellersIcon = new ImageIcon(modifiedSellersIcon);
        sellersButton = new JButton(sellersIcon);

        // Employee icon
        ImageIcon employeeIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\employee.png");
        Image employeeIconImage = employeeIcon.getImage();
        Image modifiedEmployeeIcon = employeeIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        employeeIcon = new ImageIcon(modifiedEmployeeIcon);
        employeesButton = new JButton(employeeIcon);

        // Add icon
        ImageIcon addIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\add.png");
        Image addIconImage = addIcon.getImage();
        Image modifiedAddIcon = addIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(modifiedAddIcon);
        addButton = new JButton(addIcon);

        // Delete icon
        ImageIcon deleteIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\delete.png");
        Image deleteIconImage = deleteIcon.getImage();
        Image modifiedDeleteIcon = deleteIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(modifiedDeleteIcon);
        deleteButton = new JButton(deleteIcon);

        // Pay icon
        ImageIcon payIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\payInvoice.png");
        Image payIconImage = payIcon.getImage();
        Image modifiedPayIcon = payIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        payIcon = new ImageIcon(modifiedPayIcon);
        payInvoiceButton = new JButton(payIcon);
    }
}
