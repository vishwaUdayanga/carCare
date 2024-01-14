package Views;

import Controllers.OrderController;
import Models.RepaintOrder;
import Models.RepairOrder;
import Models.SendMailWithAttachment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class RepaintOrderView extends JFrame {
    public JPanel dashboardPanel;
    private JPanel header;
    private JPanel headerInner;
    private JLabel home;
    private JLabel reports;
    private JLabel employees;
    private JLabel sellers;
    private JPanel orderViewLeftSide;
    private JPanel paying;
    private JTextField qty;
    private JTextField email;
    private JTextField description;
    private JTextField employeeEmail;
    private JTextField servicePrice;
    private JTextField productName;
    private JTextField price;
    private JTable orders;
    private JPanel dashboardRight;
    private JPanel topImage;
    private JPanel orderList;
    private JPanel bottomLine;
    private JPanel buttonContainer;
    private JButton addButton;
    private JButton deleteButton;
    private JButton payInvoiceButton;
    private JButton statusBtn;

    OrderController orderController;

    public RepaintOrderView() {
        updateTable();
        employeeEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (employeeEmail.getText().equals("Employee Email")) {
                    employeeEmail.setText("");
                }
            }
        });
        employeeEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (employeeEmail.getText().isEmpty()) {
                    employeeEmail.setText("Employee Email");
                }
            }
        });
        productName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (productName.getText().equals("Product name")) {
                    productName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (productName.getText().isEmpty()) {
                    productName.setText("Product name");
                }
            }
        });
        price.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (price.getText().equals("Price")) {
                    price.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (price.getText().isEmpty()) {
                    price.setText("Price");
                }
            }
        });
        qty.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (qty.getText().equals("Qty")) {
                    qty.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (qty.getText().isEmpty()) {
                    qty.setText("Qty");
                }
            }
        });
        email.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals("Customer Email")) {
                    email.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().isEmpty()) {
                    email.setText("Customer Email");
                }
            }
        });
        description.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (description.getText().equals("Description")) {
                    description.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (description.getText().isEmpty()) {
                    description.setText("Description");
                }
            }
        });
        servicePrice.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (servicePrice.getText().equals("Service price")) {
                    servicePrice.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (servicePrice.getText().isEmpty()) {
                    servicePrice.setText("Service price");
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerEmail = email.getText();
                String des = description.getText();
                Double amount = Double.parseDouble(servicePrice.getText());
                String empEmail = employeeEmail.getText();
                orderController = new OrderController();
                boolean status = true;

                RepaintOrder repaintOrder = orderController.addRepaintOrder(customerEmail, amount, des, empEmail);

                try {
                    SendMailWithAttachment sendMailWithAttachment = new SendMailWithAttachment();
                    sendMailWithAttachment.send(empEmail,"New Repaint Order","A new repaint order has been appointed to you!");
                    System.out.println("Message sent");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(dashboardPanel, "Email is invalid", "Error", 0);
                    status = false;
                }

                if(status){
                    if(orderController.addRepaintOrderToDatabase())
                    {
                        JOptionPane.showMessageDialog(dashboardPanel, "Successfully Added a repaint order to Database", "Sucess", 1);
                        updateTable();
                    }else {
                        JOptionPane.showMessageDialog(dashboardPanel, "Cannot insert a repaint order to DB", "Error", 1);
                    }
                }else{
                    JOptionPane.showMessageDialog(dashboardPanel, "Email is invalid", "Error", 0);
                }

                employeeEmail.setText("Employee Email");
                description.setText("Description");
                servicePrice.setText("Service price");
                email.setText("Customer Email");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                int selectedIndex = orders.getSelectedRow();

                String id = model.getValueAt(selectedIndex, 0).toString();
                orderController = new OrderController();
                try {
                    if(orderController.deleteRepaintOrder(id)){
                        JOptionPane.showMessageDialog(dashboardPanel, "Repaint order deleted.", "Success", 1);
                        updateTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(dashboardPanel, "Could not delete the repaint order", "Error", 0);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not delete repaint order", "Error", 0);
                }
            }
        });
        statusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                int selectedIndex = orders.getSelectedRow();

                String status = model.getValueAt(selectedIndex, 3).toString();
                String id = model.getValueAt(selectedIndex, 0).toString();
                String customerEmail = model.getValueAt(selectedIndex,1).toString();
                orderController = new OrderController();

                if (status.equals("0")) {
                    try {
                        if(orderController.updateRepaintOrderStatus(id)){
                            JOptionPane.showMessageDialog(dashboardPanel, "Repaint order is ready now.", "Success", 1);
                            SendMailWithAttachment sendMailWithAttachment = new SendMailWithAttachment();
                            sendMailWithAttachment.send(customerEmail,"Vehicle is ready","Your vehicle has been repainted. Please come and collect your vehicle!");
                            System.out.println("Message sent");
                            updateTable();
                        }
                        else {
                            JOptionPane.showMessageDialog(dashboardPanel, "Could not update the repaint order.", "Error", 0);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dashboardPanel, "Could not update the repaint order.", "Error", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(dashboardPanel, "Already updated this repaint order.", "Error", 0);
                }
            }
        });
    }

    public void updateTable() {
        orderController = new OrderController();
        try {
            ResultSet repairOrders = orderController.findRepaintOrder();
            ResultSetMetaData resultSetMetaData = repairOrders.getMetaData();
            int c = resultSetMetaData.getColumnCount();

            DefaultTableModel model = (DefaultTableModel)orders.getModel();
            model.setRowCount(0);
            Vector vector = new Vector();

            for (int i=1; i<=c; i++) {
                vector.add(repairOrders.getString("order_id"));
                vector.add(repairOrders.getString("customer_email"));
                vector.add(repairOrders.getString("employee_email"));
                vector.add(repairOrders.getString("status"));
                vector.add(repairOrders.getString("description"));
                vector.add(repairOrders.getString("amount"));
            }
            model.addRow(vector);
            while (repairOrders.next()) {
                Vector vector1 = new Vector();

                for (int i=1; i<=c; i++) {
                    vector1.add(repairOrders.getString("order_id"));
                    vector1.add(repairOrders.getString("customer_email"));
                    vector1.add(repairOrders.getString("employee_email"));
                    vector1.add(repairOrders.getString("status"));
                    vector1.add(repairOrders.getString("description"));
                    vector1.add(repairOrders.getString("amount"));
                }
                model.addRow(vector1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch repaint order details", "Error", 0);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        RepaintOrderView repaintOrderView = new RepaintOrderView();
        repaintOrderView.setContentPane(repaintOrderView.dashboardPanel);
        repaintOrderView.setTitle("Repaint orders");
        repaintOrderView.setSize(800, 500);
        repaintOrderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        repaintOrderView.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        repaintOrderView.setLocation(dim.width/2-repaintOrderView.getSize().width/2, dim.height/2-repaintOrderView.getSize().height/2);
    }

    private void createUIComponents() {
        Border emptyBorder = BorderFactory.createLineBorder(Color.white);
        addButton = new JButton();
        addButton.setBorder(emptyBorder);

        statusBtn = new JButton();
        statusBtn.setBorder(emptyBorder);

        deleteButton = new JButton();
        deleteButton.setBorder(emptyBorder);

        payInvoiceButton = new JButton();
        payInvoiceButton.setBorder(emptyBorder);
        payInvoiceButton.setMargin(new Insets(50, 50, 50, 50));

        dashboardRight = new JPanel();
        Border blackline = BorderFactory.createLineBorder(new Color(200, 200, 200, 255));
        dashboardRight.setBorder(blackline);

        paying = new JPanel();
        paying.setBorder(blackline);

        Image image = Toolkit.getDefaultToolkit().getImage("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\repair2.jpg");
        topImage = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 260, 170,  this);
            }
        };

        Image umbrellaImg = Toolkit.getDefaultToolkit().getImage("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\orderView4.png");

        orderList = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(umbrellaImg, 0, 0, 200, 150,  this);
            }
        };

        ImageIcon homeIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\home2.png");
        Image homeIconImage = homeIcon.getImage();
        Image modifiedHomeIcon = homeIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(modifiedHomeIcon);
        home = new JLabel( homeIcon);

        ImageIcon reportIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\reports2.png");
        Image reportIconImage = reportIcon.getImage();
        Image modifiedReportIcon = reportIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        reportIcon = new ImageIcon(modifiedReportIcon);
        reports = new JLabel( reportIcon);

        ImageIcon employeeIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\employee1.png");
        Image employeeIconImage = employeeIcon.getImage();
        Image modifiedEmployeeIcon = employeeIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        employeeIcon = new ImageIcon(modifiedEmployeeIcon);
        employees = new JLabel( employeeIcon);

        ImageIcon sellerIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\dollar.png");
        Image sellerIconIconImage = sellerIcon.getImage();
        Image modifiedSellerIcon = sellerIconIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        sellerIcon = new ImageIcon(modifiedSellerIcon);
        sellers = new JLabel( sellerIcon);

        headerInner = new JPanel();
        headerInner.setBackground(new Color(0, 0, 0, 0));
        headerInner.setOpaque(false);
        header = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 147, 255, 255), getWidth(), getHeight(), new Color(9, 64, 121,255 ), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        bottomLine = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 147, 255, 255), getWidth(), getHeight(), new Color(9, 64, 121,255 ), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        buttonContainer = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(219, 30, 0), getWidth(), getHeight(), new Color(200, 150, 10 ), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        employeeEmail = new RoundedJTextField(20);
        employeeEmail.setText("Employee Email");

        productName = new RoundedJTextField(20);
        productName.setText("Product name");

        price = new RoundedJTextField(20);
        price.setText("Price");

        qty = new RoundedJTextField(20);
        qty.setText("Qty");

        email = new RoundedJTextField(20);
        email.setText("Customer Email");

        description = new RoundedJTextField(20);
        description.setText("Description");

        servicePrice = new RoundedJTextField(20);
        servicePrice.setText("Service price");

        DefaultTableModel model = new DefaultTableModel();
        orders = new JTable(model);

        // Create a couple of columns
        model.addColumn("Col1");
        model.addColumn("Col2");
        model.addColumn("Col3");
        model.addColumn("Col4");
        model.addColumn("Col5");
        model.addColumn("Col6");

        // Append a row

    }

    // implement a round-shaped JTextField
    class RoundedJTextField extends JTextField {
        private Shape shape;
        public RoundedJTextField(int size) {
            super(size);
            setBounds(520, 284, 190, 25);
            setBackground(new Color(0, 0, 0, 0));
            setOpaque(false);
            setMargin(new Insets(5, 20, 5, 20));
        }
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(0, 0, 0, 0));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(new Color(165, 165, 165, 255));
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
            return shape.contains(x, y);
        }
    }
}
