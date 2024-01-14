package Views;

import Controllers.SparePartsController;
import Controllers.SupplierController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class SpareParts extends JFrame {
    public JPanel dashboardPanel;
    private JPanel header;
    private JPanel headerInner;
    private JLabel home;
    private JLabel reports;
    private JLabel employees;
    private JLabel sellers;
    private JPanel orderViewLeftSide;
    private JTextField productCode;
    private JTextField productName;
    private JTable orders;
    private JPanel dashboardRight;
    private JPanel topImage;
    private JPanel orderList;
    private JPanel bottomLine;
    private JPanel buttonContainer;
    private JButton addButton;
    private JButton deleteButton;
    private JButton EditBtn;
    private JTextField price;

    SparePartsController sparePartsController;

    public SpareParts() {
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
        updateTable();
        productCode.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (productCode.getText().equals("Item Name")) {
                    productCode.setText("");
                }
            }
        });
        productCode.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (productCode.getText().isEmpty()) {
                    productCode.setText("Item Name");
                }
            }
        });
        productName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (productName.getText().equals("Brand")) {
                    productName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (productName.getText().isEmpty()) {
                    productName.setText("Brand");
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = productCode.getText();
                String itemBrand = productName.getText();
                Double itemPrice = Double.parseDouble(price.getText());
                sparePartsController = new SparePartsController();
                Models.SparePart sparePart = sparePartsController.addItem(itemName,itemBrand, itemPrice);
                if(sparePartsController.addSparePartsToDB()){
                    JOptionPane.showMessageDialog(dashboardPanel, "Spare part Inserted", "Success", 1);
                    updateTable();
                }
                else{
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not add spare part", "Error", 0);
                }
                productCode.setText("Item Name");
                productName.setText("Brand");
                price.setText("Price");
            }
        });
        orders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                int selectedIndex = orders.getSelectedRow();

                productCode.setText(model.getValueAt(selectedIndex, 1).toString());
                productName.setText(model.getValueAt(selectedIndex, 2).toString());
                price.setText(model.getValueAt(selectedIndex, 3).toString());
                super.mouseClicked(e);
            }
        });
        EditBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                int selectedIndex = orders.getSelectedRow();

                int itemId = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                String itemName = productCode.getText();
                String itemBrand = productName.getText();
                Double itemPrice = Double.parseDouble(price.getText());
                sparePartsController = new SparePartsController();
                try {
                    if(sparePartsController.updateSparePart(itemId, itemName, itemBrand, itemPrice)){
                        JOptionPane.showMessageDialog(dashboardPanel, "Spare part updated", "Success", 1);
                        updateTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(dashboardPanel, "Could not update spare parts", "Error", 0);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not update spare parts", "Error", 0);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                int selectedIndex = orders.getSelectedRow();

                int itemId = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                sparePartsController = new SparePartsController();
                try {
                    if(sparePartsController.deleteSparePart(itemId)){
                        JOptionPane.showMessageDialog(dashboardPanel, "Spare part deleted", "Success", 1);
                        updateTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(dashboardPanel, "Could not delete spare part", "Error", 0);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not delete spare part", "Error", 0);
                }
            }
        });
    }

    public void updateTable() {
        sparePartsController = new SparePartsController();
        try {
            ResultSet items = sparePartsController.findSpareParts();
            ResultSetMetaData resultSetMetaData = items.getMetaData();
            int c = resultSetMetaData.getColumnCount();
            System.out.println(c);

            DefaultTableModel model = (DefaultTableModel)orders.getModel();
            model.setRowCount(0);
            Vector vector = new Vector();

            for (int i=1; i<=c; i++) {
                vector.add(items.getString("id"));
                vector.add(items.getString("name"));
                vector.add(items.getString("brand"));
                vector.add(items.getString("price"));
                vector.add(items.getString("qty"));
            }
            model.addRow(vector);
            while (items.next()) {
                Vector vector1 = new Vector();

                for (int i=1; i<=c; i++) {
                    vector1.add(items.getString("id"));
                    vector1.add(items.getString("name"));
                    vector1.add(items.getString("brand"));
                    vector1.add(items.getString("price"));
                    vector1.add(items.getString("qty"));
                }
                model.addRow(vector1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch spare parts details", "Error", 0);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SpareParts spareParts = new SpareParts();
        spareParts.setContentPane(spareParts.dashboardPanel);
        spareParts.setTitle("Manage Inventory");
        spareParts.setSize(800, 500);
        spareParts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        spareParts.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        spareParts.setLocation(dim.width/2-spareParts.getSize().width/2, dim.height/2-spareParts.getSize().height/2);
    }

    private void createUIComponents() {
        Border emptyBorder = BorderFactory.createLineBorder(Color.white);
        addButton = new JButton();
        addButton.setBorder(emptyBorder);

        deleteButton = new JButton();
        deleteButton.setBorder(emptyBorder);

        EditBtn = new JButton();
        EditBtn.setBorder(emptyBorder);


        dashboardRight = new JPanel();
        Border blackline = BorderFactory.createLineBorder(new Color(200, 200, 200, 255));
        dashboardRight.setBorder(blackline);


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

        productCode = new SpareParts.RoundedJTextField(20);
        productCode.setText("Item Name");

        productName = new SpareParts.RoundedJTextField(20);
        productName.setText("Brand");

        price = new SpareParts.RoundedJTextField(20);
        price.setText("Price");

        DefaultTableModel model = new DefaultTableModel();
        orders = new JTable(model);

        // Create a couple of columns
        model.addColumn("Col1");
        model.addColumn("Col2");
        model.addColumn("Col3");
        model.addColumn("Col4");
        model.addColumn("Col5");

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
