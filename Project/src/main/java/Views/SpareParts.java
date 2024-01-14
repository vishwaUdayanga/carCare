package Views;

import Controllers.SparePartsController;
import Controllers.SupplierController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class SpareParts extends JFrame {
    private JPanel dashboardPanel;
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
                }
                else{
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not add spare part", "Error", 0);
                }
                productCode.setText("Item Name");
                productName.setText("Brand");
                price.setText("Price");
            }
        });
//        orders.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                DefaultTableModel model = (DefaultTableModel)orders.getModel();
//                int selectedIndex = orders.getSelectedRow();
//
//                productCode.setText(model.getValueAt(selectedIndex, 0).toString());
//                productName.setText(model.getValueAt(selectedIndex, 1).toString());
//                super.mouseClicked(e);
//            }
//        });
//        EditBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DefaultTableModel model = (DefaultTableModel)orders.getModel();
//                int selectedIndex = orders.getSelectedRow();
//
//                String email = model.getValueAt(selectedIndex, 0).toString();
//                String name = productName.getText();
//                employeeController = new EmployeeController();
//                try {
//                    if(employeeController.updateEmployeeDB(email, name)){
//                        JOptionPane.showMessageDialog(dashboardPanel, "Employee Updated", "Success", 1);
//                        updateTable();
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(dashboardPanel, "Could not update employee", "Error", 0);
//                    }
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(dashboardPanel, "Could not update employee", "Error", 0);
//                }
//            }
//        });
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DefaultTableModel model = (DefaultTableModel)orders.getModel();
//                int selectedIndex = orders.getSelectedRow();
//
//                String email = model.getValueAt(selectedIndex, 0).toString();
//                employeeController = new EmployeeController();
//                try {
//                    if(employeeController.deleteEmployeeDB(email)){
//                        JOptionPane.showMessageDialog(dashboardPanel, "Employee deleted", "Success", 1);
//                        updateTable();
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(dashboardPanel, "Could not delete employee", "Error", 0);
//                    }
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(dashboardPanel, "Could not delete employee", "Error", 0);
//                }
//            }
//        });
    }

//    public void updateTable() {
//        employeeController = new EmployeeController();
//        try {
//            ResultSet employees = employeeController.findEmployees();
//            ResultSetMetaData resultSetMetaData = employees.getMetaData();
//            int c = resultSetMetaData.getColumnCount();
//            System.out.println(c);
//
//            DefaultTableModel model = (DefaultTableModel)orders.getModel();
//            model.setRowCount(0);
//            Vector vector = new Vector();
//
//            for (int i=1; i<=c; i++) {
//                vector.add(employees.getString("email"));
//                vector.add(employees.getString("name"));
//            }
//            model.addRow(vector);
//            while (employees.next()) {
//                Vector vector1 = new Vector();
//
//                for (int i=1; i<=c; i++) {
//                    vector1.add(employees.getString("email"));
//                    vector1.add(employees.getString("name"));
//                }
//                model.addRow(vector1);
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch employee details", "Error", 0);
//            System.out.println(ex.getMessage());
//        }
//    }

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


        Image image = Toolkit.getDefaultToolkit().getImage("C:\\OOP\\carCare\\Project\\src\\main\\java\\Views\\Images\\orderView3.jpg");
        topImage = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 400, 150,  this);
            }
        };

        Image umbrellaImg = Toolkit.getDefaultToolkit().getImage("C:\\OOP\\carCare\\Project\\src\\main\\java\\Views\\Images\\orderView4.png");

        orderList = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(umbrellaImg, 0, 0, 200, 150,  this);
            }
        };

        ImageIcon homeIcon = new ImageIcon("C:\\OOP\\carCare\\Project\\src\\main\\java\\Views\\Images\\home2.png");
        Image homeIconImage = homeIcon.getImage();
        Image modifiedHomeIcon = homeIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(modifiedHomeIcon);
        home = new JLabel( homeIcon);

        ImageIcon reportIcon = new ImageIcon("C:\\OOP\\carCare\\Project\\src\\main\\java\\Views\\Images\\reports2.png");
        Image reportIconImage = reportIcon.getImage();
        Image modifiedReportIcon = reportIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        reportIcon = new ImageIcon(modifiedReportIcon);
        reports = new JLabel( reportIcon);

        ImageIcon employeeIcon = new ImageIcon("C:\\OOP\\carCare\\Project\\src\\main\\java\\Views\\Images\\employee1.png");
        Image employeeIconImage = employeeIcon.getImage();
        Image modifiedEmployeeIcon = employeeIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        employeeIcon = new ImageIcon(modifiedEmployeeIcon);
        employees = new JLabel( employeeIcon);

        ImageIcon sellerIcon = new ImageIcon("C:\\OOP\\carCare\\Project\\src\\main\\java\\Views\\Images\\dollar.png");
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
