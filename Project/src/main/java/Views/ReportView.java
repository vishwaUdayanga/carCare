package Views;

import Controllers.EmployeeController;
import Controllers.ReportController;
import Models.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class ReportView extends JFrame  {
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
    private JLabel Total;
    private JLabel provide;


    ReportController reportController;

    public ReportView() {
        updateTable();
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReportView.super.dispose();
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
                ReportView.super.dispose();
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
                ReportView.super.dispose();
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
        reports.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReportView.super.dispose();
                ReportView reportView = new ReportView();
                reportView.setContentPane(reportView.dashboardPanel);
                reportView.setTitle("Monthly Reports");
                reportView.setSize(800, 500);
                reportView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                reportView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                reportView.setLocation(dim.width/2-reportView.getSize().width/2, dim.height/2-reportView.getSize().height/2);
            }
        });

        provide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReportView.super.dispose();
                ProvideView provideView = new ProvideView();
                provideView.setContentPane(provideView.dashboardPanel);
                provideView.setTitle("Manage Provides");
                provideView.setSize(800, 500);
                provideView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                provideView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                provideView.setLocation(dim.width/2-provideView.getSize().width/2, dim.height/2-provideView.getSize().height/2);
                super.mouseClicked(e);
            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) orders.getModel();
                int count = orders.getRowCount();
                for (int i = count; i >= 1 ; i--){
                    model.removeRow(i-1);
                }
                model.setColumnCount(6);

                reportController= new ReportController();
                try {
                    ResultSet orderDetails = reportController.findRepaintOrderDetails();
                    ResultSetMetaData resultSetMetaData = orderDetails.getMetaData();
                    int c = resultSetMetaData.getColumnCount();

                    model.setRowCount(0);
                    Vector vector = new Vector();

                    for (int i=1; i<=c; i++) {
                        vector.add(orderDetails.getString("order_id"));
                        vector.add(orderDetails.getString("customer_email"));
                        vector.add(orderDetails.getString("employee_email"));
                        vector.add(orderDetails.getString("status"));
                        vector.add(orderDetails.getString("amount"));
                        vector.add(orderDetails.getString("date"));
                    }
                    model.addRow(vector);
                    while (orderDetails.next()) {
                        Vector vector1 = new Vector();

                        for (int i=1; i<=c; i++) {
                            vector1.add(orderDetails.getString("order_id"));
                            vector1.add(orderDetails.getString("customer_email"));
                            vector1.add(orderDetails.getString("employee_email"));
                            vector1.add(orderDetails.getString("status"));
                            vector1.add(orderDetails.getString("amount"));
                            vector1.add(orderDetails.getString("date"));
                        }
                        model.addRow(vector1);
                    }
                    ResultSet totalCount = reportController.findAllRepaintOrderDetails();
                    ResultSetMetaData resultSetMetaData1 = totalCount.getMetaData();
                    Total.setText("Rs : "+totalCount.getString("count"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch Repaint order details", "Error", 0);
                    System.out.println(ex.getMessage());
                }
               productName.setText("Employee Name");
            }
        });
        orders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel)orders.getModel();
                int selectedIndex = orders.getSelectedRow();

                productCode.setText(model.getValueAt(selectedIndex, 0).toString());
                productName.setText(model.getValueAt(selectedIndex, 1).toString());
                super.mouseClicked(e);
            }
        });
        EditBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportController= new ReportController();
                try {
                    ResultSet orderDetails = reportController.findOrderDetails();
                    ResultSetMetaData resultSetMetaData = orderDetails.getMetaData();
                    int c = resultSetMetaData.getColumnCount();

                    DefaultTableModel model = (DefaultTableModel)orders.getModel();
                    model.setRowCount(0);
                    Vector vector = new Vector();

                    for (int i=1; i<=c; i++) {
                        vector.add(orderDetails.getString("order_id"));
                        vector.add(orderDetails.getString("customer_email"));
                        vector.add(orderDetails.getString("amount"));
                        vector.add(orderDetails.getString("date"));
                    }
                    model.addRow(vector);
                    while (orderDetails.next()) {
                        Vector vector1 = new Vector();

                        for (int i=1; i<=c; i++) {
                            vector1.add(orderDetails.getString("order_id"));
                            vector1.add(orderDetails.getString("customer_email"));
                            vector1.add(orderDetails.getString("amount"));
                            vector1.add(orderDetails.getString("date"));
                        }
                        model.addRow(vector1);
                    }
                    ResultSet totalCount = reportController.findAllOrderDetails();
                    ResultSetMetaData resultSetMetaData1 = totalCount.getMetaData();
                    Total.setText("Rs : "+totalCount.getString("count"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch order details", "Error", 0);
                    System.out.println(ex.getMessage());
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = orders.getRowCount();
                DefaultTableModel model = (DefaultTableModel) orders.getModel();
                int count = orders.getRowCount();
                for (int i = count; i >= 1 ; i--){
                    model.removeRow(i-1);
                }

                model.setColumnCount(6);
                reportController= new ReportController();
                try {
                    ResultSet orderDetails = reportController.findRepairOrderDetails();
                    ResultSetMetaData resultSetMetaData = orderDetails.getMetaData();
                    int c = resultSetMetaData.getColumnCount();

                    model.setRowCount(0);
                    Vector vector = new Vector();

                    for (int i=1; i<=c; i++) {
                        vector.add(orderDetails.getString("order_id"));
                        vector.add(orderDetails.getString("customer_email"));
                        vector.add(orderDetails.getString("employee_email"));
                        vector.add(orderDetails.getString("status"));
                        vector.add(orderDetails.getString("amount"));
                        vector.add(orderDetails.getString("date"));
                    }
                    model.addRow(vector);
                    while (orderDetails.next()) {
                        Vector vector1 = new Vector();

                        for (int i=1; i<=c; i++) {
                            vector1.add(orderDetails.getString("order_id"));
                            vector1.add(orderDetails.getString("customer_email"));
                            vector1.add(orderDetails.getString("employee_email"));
                            vector1.add(orderDetails.getString("status"));
                            vector1.add(orderDetails.getString("amount"));
                            vector1.add(orderDetails.getString("date"));
                        }
                        model.addRow(vector1);
                    }
                    ResultSet totalCount = reportController.findAllRepairOrderDetails();
                    ResultSetMetaData resultSetMetaData1 = totalCount.getMetaData();
                    Total.setText("Rs : "+totalCount.getString("count"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch Repair orders", "Error", 0);
                    System.out.println(ex.getMessage());
                }

            }
        });
    }

    public void updateTable() {
         reportController= new ReportController();
        try {
            ResultSet orderDetails = reportController.findOrderDetails();
            ResultSetMetaData resultSetMetaData = orderDetails.getMetaData();
            int c = resultSetMetaData.getColumnCount();

            DefaultTableModel model = (DefaultTableModel)orders.getModel();
            model.setRowCount(0);
            Vector vector = new Vector();

            for (int i=1; i<=c; i++) {
                vector.add(orderDetails.getString("order_id"));
                vector.add(orderDetails.getString("customer_email"));
                vector.add(orderDetails.getString("amount"));
                vector.add(orderDetails.getString("date"));
            }
            model.addRow(vector);
            while (orderDetails.next()) {
                Vector vector1 = new Vector();

                for (int i=1; i<=c; i++) {
                    vector1.add(orderDetails.getString("order_id"));
                    vector1.add(orderDetails.getString("customer_email"));
                    vector1.add(orderDetails.getString("amount"));
                    vector1.add(orderDetails.getString("date"));
                }
                model.addRow(vector1);
            }
            ResultSet totalCount = reportController.findAllOrderDetails();
            ResultSetMetaData resultSetMetaData1 = totalCount.getMetaData();
            Total.setText("Rs : "+totalCount.getString("count"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dashboardPanel, "Could not fetch order details", "Error", 0);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ReportView reportView = new ReportView();
        reportView.setContentPane(reportView.dashboardPanel);
        reportView.setTitle("Monthly Reports");
        reportView.setSize(800, 500);
        reportView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reportView.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        reportView.setLocation(dim.width/2-reportView.getSize().width/2, dim.height/2-reportView.getSize().height/2);
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

        ImageIcon provideIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\reports2.png");
        Image provideIconImage = provideIcon.getImage();
        Image modifiedprovideIcon = provideIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        provideIcon = new ImageIcon(modifiedprovideIcon);
        provide = new JLabel( provideIcon);

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

        productCode = new ReportView.RoundedJTextField(20);
        productCode.setText("Employee Email");

        productName = new ReportView.RoundedJTextField(20);
        productName.setText("Employee Name");


        DefaultTableModel model = new DefaultTableModel();
        orders = new JTable(model);

        model.setColumnCount(4);


    }

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
