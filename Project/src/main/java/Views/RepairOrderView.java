package Views;

import Controllers.OrderController;
import Models.RepairOrder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class RepairOrderView extends JFrame {
    public JPanel dashboardPanel;
    private JPanel header;
    private JPanel headerInner;
    private JLabel home;
    private JLabel reports;
    private JLabel employees;
    private JLabel sellers;
    private JPanel orderViewLeftSide;
    private JPanel paying;
    private JTextField employeeEmail;
    private JTextField productName;
    private JTextField price;
    private JTextField qty;
    private JTable orders;
    private JPanel dashboardRight;
    private JPanel topImage;
    private JPanel orderList;
    private JPanel bottomLine;
    private JPanel buttonContainer;
    private JButton addButton;
    private JButton deleteButton;
    private JButton payInvoiceButton;
    private JTextField email;
    private JTextField servicePrice;
    private JTextField description;

    OrderController orderController;

    public RepairOrderView() {
        employeeEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (employeeEmail.getText().equals("Product code")) {
                    employeeEmail.setText("");
                }
            }
        });
        employeeEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (employeeEmail.getText().isEmpty()) {
                    employeeEmail.setText("Product code");
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
                if (email.getText().equals("Email")) {
                    email.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().isEmpty()) {
                    email.setText("Email");
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

                RepairOrder repairOrder = orderController.addRepairOrder(customerEmail, amount, des, empEmail);

                if(orderController.addRepairOrderToDatabase())
                {
                    JOptionPane.showMessageDialog(dashboardPanel, "Successfully Added a repair order to Database", "Sucess", 1);
                }else {
                    JOptionPane.showMessageDialog(dashboardPanel, "Cannot insert a repair order to DB", "Error", 1);
                }
            }
        });
    }

    public static void main(String[] args) {
        RepairOrderView repairOrderView = new RepairOrderView();
        repairOrderView.setContentPane(repairOrderView.dashboardPanel);
        repairOrderView.setTitle("Repair orders");
        repairOrderView.setSize(800, 500);
        repairOrderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        repairOrderView.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        repairOrderView.setLocation(dim.width/2-repairOrderView.getSize().width/2, dim.height/2-repairOrderView.getSize().height/2);
    }

    private void createUIComponents() {
        Border emptyBorder = BorderFactory.createLineBorder(Color.white);
        addButton = new JButton();
        addButton.setBorder(emptyBorder);

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

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(129, 209, 100), getWidth(), getHeight(), new Color(44, 181, 88 ), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        employeeEmail = new RoundedJTextField(20);
        employeeEmail.setText("Product code");

        productName = new RoundedJTextField(20);
        productName.setText("Product name");

        price = new RoundedJTextField(20);
        price.setText("Price");

        qty = new RoundedJTextField(20);
        qty.setText("Qty");

        email = new RoundedJTextField(20);
        email.setText("Email");

        description = new RoundedJTextField(20);
        description.setText("Description");

        servicePrice = new RoundedJTextField(20);
        servicePrice.setText("Service price");

        DefaultTableModel model = new DefaultTableModel();
        orders = new JTable(model);
        orders.setBackground(new Color(43, 45, 48, 0));
        orders.setBorder(blackline);

        // Create a couple of columns
        model.addColumn("Col1");
        model.addColumn("Col2");
        model.addColumn("Col3");
        model.addColumn("Col4");

        // Append a row
        model.addRow(new Object[]{"Order Id", "Amount", "Date", "Email"});
        model.addRow(new Object[]{"1", "Amount", "Date", "Email"});
        model.addRow(new Object[]{"2", "Amount", "Date", "Email"});
        model.addRow(new Object[]{"3", "Amount", "Date", "Email"});
        model.addRow(new Object[]{"4", "Amount", "Date", "Email"});

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
