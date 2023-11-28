package Views;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {
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

    public static void main(String[] args) {
        OrderView orderView = new OrderView();
        orderView.setContentPane(orderView.backPane);
        orderView.setTitle("Order View");
        orderView.setSize(1200, 600);
        orderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderView.setVisible(true);
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
    }
}
