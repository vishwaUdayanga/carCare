package Views;

import Controllers.OrderController;
import Models.Order;
import Models.RepairOrder;
import Models.SalesProduct;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;

public class OrderView extends JFrame {
    public JPanel dashboardPanel;
    private JPanel header;
    private JPanel headerInner;
    private JLabel home;
    private JLabel reports;
    private JLabel employees;
    private JLabel sellers;
    private JPanel dashboardRight;
    private JPanel topImage;
    private JPanel orderList;
    private JPanel orderViewLeftSide;
    private JPanel bottomLine;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel buttonContainer;
    private JPanel paying;
    private JButton payInvoiceButton;
    private JTextField productCode;
    private JTextField productName;
    private JTextField price;
    private JTextField qty;
    private JTable orders;
    private JLabel subTotal;
    private JLabel amountFinal;
    private JTextField customerEmail;

    OrderController orderController;


    public OrderView() {
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DashboardView dashboardView = new DashboardView();
                dashboardView.setContentPane(dashboardView.dashboardPanel);
                dashboardView.setTitle("Dashboard");
                dashboardView.setSize(800, 500);
                dashboardView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dashboardView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                dashboardView.setLocation(dim.width/2-dashboardView.getSize().width/2, dim.height/2-dashboardView.getSize().height/2);
            }
        });
        employees.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeView employeeView = new EmployeeView();
                employeeView.setContentPane(employeeView.dashboardPanel);
                employeeView.setTitle("Manage Employees");
                employeeView.setSize(800, 500);
                employeeView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                employeeView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                employeeView.setLocation(dim.width/2-employeeView.getSize().width/2, dim.height/2-employeeView.getSize().height/2);
            }
        });
        sellers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Supplier supplier = new Supplier();
                supplier.setContentPane(supplier.dashboardPanel);
                supplier.setTitle("Manage Suppliers");
                supplier.setSize(800, 500);
                supplier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                supplier.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                supplier.setLocation(dim.width/2-supplier.getSize().width/2, dim.height/2-supplier.getSize().height/2);
            }
        });
        productCode.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (productCode.getText().equals("Product code")) {
                    productCode.setText("");
                }
            }
        });
        productCode.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (productCode.getText().isEmpty()) {
                    productCode.setText("Product code");
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

        customerEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (customerEmail.getText().equals("Email")) {
                    customerEmail.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (customerEmail.getText().isEmpty()) {
                    customerEmail.setText("Email");
                }
            }
        });
        productCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int productId = Integer.parseInt(productCode.getText());
                    orderController = new OrderController();
                    ResultSet resultSet = orderController.findProductsFromDatabaseById(productId);
                    try {
                        String name = resultSet.getString("name");
                        Double productPrice = resultSet.getDouble("price");

                        productName.setText(name);
                        price.setText(String.valueOf(productPrice));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dashboardPanel, "Product not found", "Error", 0);
                    }

                }
                super.keyPressed(e);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(productCode.getText());
                String name = productName.getText();
                Double productPrice = Double.parseDouble(price.getText());
                int customerQty = Integer.parseInt(qty.getText());
                Double amount = productPrice * customerQty;

                ResultSet resultSet = orderController.findProductsFromDatabaseById(productId);
                try {
                    boolean status = true;
                    int actualQty = resultSet.getInt(("qty"));

                    if (actualQty < customerQty ) {
                        JOptionPane.showMessageDialog(dashboardPanel, "Available qty : " + actualQty, "Error", 0);
                        JOptionPane.showMessageDialog(dashboardPanel, "Not available qty", "Error", 0);
                    } else {
                        DefaultTableModel model = (DefaultTableModel)orders.getModel();
                        for (int i =1; i<orders.getRowCount(); i++) {
                            if (orders.getValueAt(i, 0).equals(productId)) {
                                status = false;
                            }
                        }

                        if (status) {
                            model.addRow(new Object[]{productId, name, amount, customerQty});
                        } else {
                            JOptionPane.showMessageDialog(dashboardPanel, "This product is already added.", "Error", 0);
                        }

                        productCode.setText("Product code");
                        productName.setText("Product name");
                        price.setText("Price");
                        qty.setText("Qty");

                        Double sum =0.0;
                        for (int i=1; i<orders.getRowCount(); i++) {
                            sum += Double.parseDouble(orders.getValueAt(i, 2).toString());
                        }

                        subTotal.setText(Double.toString(sum));
                        amountFinal.setText(Double.toString(sum));
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Product not found", "Error", 0);
                    System.out.println(ex.getMessage());
                }
            }
        });
        orders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                model.removeRow(orders.getSelectedRow());
                Double sum =0.0;
                for (int i=1; i<orders.getRowCount(); i++) {
                    sum += Double.parseDouble(orders.getValueAt(i, 2).toString());
                }

                subTotal.setText(Double.toString(sum));
                amountFinal.setText(Double.toString(sum));
            }
        });
        payInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double total = Double.parseDouble(amountFinal.getText());
                String email = customerEmail.getText();
                Order order = orderController.addOrder(email, total);
                int lastIndex = 0;

                try {
                    ResultSet resultSet = orderController.addOrderToDatabase();

                    if (resultSet.next()) {
                        lastIndex = resultSet.getInt(1);
                    }

                    int rowCount = orders.getRowCount();

                    int productId = 0;
                    int orderId = 0;
                    int qty = 0;
                    Double amount = 0.0;

                    SalesProduct salesProduct = null;

                    for (int i =1; i<rowCount; i++) {
                        productId = (int)orders.getValueAt(i, 0);
                        qty = (int)orders.getValueAt(i, 3);
                        amount = (Double)orders.getValueAt(i, 2);
                        salesProduct = orderController.addSalesProduct(productId, lastIndex, amount, qty);
                        if(!orderController.addSalesProductToDatabase()) {
                            JOptionPane.showMessageDialog(dashboardPanel, "Could not add products", "Error", 0);
                        }

                        if (!orderController.reduceQty(productId, qty)) {
                            JOptionPane.showMessageDialog(dashboardPanel, "Could not reduce qty", "Error", 0);
                        }
                    }

                    JOptionPane.showMessageDialog(dashboardPanel, "Successfully Added a order to Database", "Sucess", 1);
                    customerEmail.setText("Email");
                    subTotal.setText("0");
                    amountFinal.setText("0");
                    DefaultTableModel model = (DefaultTableModel) orders.getModel();
                    int count = model.getRowCount();
                    for (int i = rowCount; i > 1 ; i--){
                        model.removeRow(i-1);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Cannot insert a repair order to DB : " + ex.getMessage(), "Error", 1);
                }
            }
        });

    }

    public static void main(String[] args) {
        OrderView orderView = new OrderView();
        orderView.setContentPane(orderView.dashboardPanel);
        orderView.setTitle("Regular Orders");
        orderView.setSize(800, 500);
        orderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderView.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        orderView.setLocation(dim.width/2-orderView.getSize().width/2, dim.height/2-orderView.getSize().height/2);
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

        Image image = Toolkit.getDefaultToolkit().getImage("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\orderView3.jpg");
        topImage = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 400, 150,  this);
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

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(251, 139, 36), getWidth(), getHeight(), new Color(251, 209, 50 ), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        productCode = new RoundedJTextField(20);
        productCode.setText("Product code");

        productName = new RoundedJTextField(20);
        productName.setText("Product name");

        price = new RoundedJTextField(20);
        price.setText("Price");

        qty = new RoundedJTextField(20);
        qty.setText("Qty");


        customerEmail = new RoundedJTextField(20);
        customerEmail.setText("Email");

        DefaultTableModel model = new DefaultTableModel();
        orders = new JTable(model);

        // Create a couple of columns
        model.addColumn("Col1");
        model.addColumn("Col2");
        model.addColumn("Col3");
        model.addColumn("Col4");

        // Append a row
        model.addRow(new Object[]{"Product id", "Name", "Price", "QTY"});

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
