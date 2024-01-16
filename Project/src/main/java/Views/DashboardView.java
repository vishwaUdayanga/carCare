package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardView extends JFrame {

    public JPanel dashboardPanel;
    private JLabel home;
    private JLabel reports;
    private JLabel employees;
    private JLabel sellers;
    private JPanel header;
    private JPanel headerInner;
    private JPanel topImage;
    private JPanel dashboardRight;
    private JPanel orderList;
    private JLabel regularOrders;
    private JLabel repaintOrders;
    private JLabel repairOrders;
    private JLabel inventory;
    private JLabel provide;


    public DashboardView() {
        regularOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DashboardView.super.dispose();
                OrderView orderView = new OrderView();
                orderView.setContentPane(orderView.dashboardPanel);
                orderView.setTitle("Regular Orders");
                orderView.setSize(800, 500);
                orderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                orderView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                orderView.setLocation(dim.width/2-orderView.getSize().width/2, dim.height/2-orderView.getSize().height/2);
            }
        });
        repairOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DashboardView.super.dispose();
                RepairOrderView repairOrderView = new RepairOrderView();
                repairOrderView.setContentPane(repairOrderView.dashboardPanel);
                repairOrderView.setTitle("Repair orders");
                repairOrderView.setSize(800, 500);
                repairOrderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                repairOrderView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                repairOrderView.setLocation(dim.width/2-repairOrderView.getSize().width/2, dim.height/2-repairOrderView.getSize().height/2);
            }
        });
        repaintOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DashboardView.super.dispose();
                RepaintOrderView repaintOrderView = new RepaintOrderView();
                repaintOrderView.setContentPane(repaintOrderView.dashboardPanel);
                repaintOrderView.setTitle("Repaint orders");
                repaintOrderView.setSize(800, 500);
                repaintOrderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                repaintOrderView.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                repaintOrderView.setLocation(dim.width/2-repaintOrderView.getSize().width/2, dim.height/2-repaintOrderView.getSize().height/2);
            }
        });
        inventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DashboardView.super.dispose();
                SpareParts spareParts = new SpareParts();
                spareParts.setContentPane(spareParts.dashboardPanel);
                spareParts.setTitle("Manage Inventory");
                spareParts.setSize(800, 500);
                spareParts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                spareParts.setVisible(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                spareParts.setLocation(dim.width/2-spareParts.getSize().width/2, dim.height/2-spareParts.getSize().height/2);
            }
        });
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DashboardView.super.dispose();
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
                DashboardView.super.dispose();
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
                DashboardView.super.dispose();
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
                DashboardView.super.dispose();
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
                DashboardView.super.dispose();
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
    }

    public static void main(String[] args) {
        DashboardView dashboardView = new DashboardView();
        dashboardView.setContentPane(dashboardView.dashboardPanel);
        dashboardView.setTitle("Dashboard");
        dashboardView.setSize(800, 500);
        dashboardView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardView.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dashboardView.setLocation(dim.width/2-dashboardView.getSize().width/2, dim.height/2-dashboardView.getSize().height/2);
    }

    private void createUIComponents() {
        dashboardRight = new JPanel();
        Border blackline = BorderFactory.createLineBorder(new Color(200, 200, 200, 255));
        dashboardRight.setBorder(blackline);

        Image image = Toolkit.getDefaultToolkit().getImage("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\tools2.jpg");
        topImage = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 400, 120,  this);
            }
        };

        Image umbrellaImg = Toolkit.getDefaultToolkit().getImage("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\umbrella.jpg");

        orderList = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(umbrellaImg, 0, 0, 200, 150,  this);
            }
        };

        ImageIcon regualrOrderIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\regularOrders.png");
        Image regualrOrderIconImage = regualrOrderIcon.getImage();
        Image modifiedRegualrOrderIconImage = regualrOrderIconImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        regualrOrderIcon = new ImageIcon(modifiedRegualrOrderIconImage);
        regularOrders = new JLabel( regualrOrderIcon);

        ImageIcon repairOrderIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\repair.png");
        Image repairOrderIconImage = repairOrderIcon.getImage();
        Image modifiedRepairOrderImage = repairOrderIconImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        repairOrderIcon = new ImageIcon(modifiedRepairOrderImage);
        repairOrders = new JLabel( repairOrderIcon);

        ImageIcon repaintOrderIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\paint.png");
        Image repaintOrderIconImage = repaintOrderIcon.getImage();
        Image modifiedRepaintOrderImage = repaintOrderIconImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        repaintOrderIcon = new ImageIcon(modifiedRepaintOrderImage);
        repaintOrders = new JLabel( repaintOrderIcon);

        ImageIcon inventoryIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\inventory1.png");
        Image inventoryIconImage = inventoryIcon.getImage();
        Image modifiedinventoryIconImage = inventoryIconImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        inventoryIcon = new ImageIcon(modifiedinventoryIconImage);
        inventory = new JLabel( inventoryIcon);


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

        ImageIcon provideIcon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\reports2.png");
        Image provideIconImage = provideIcon.getImage();
        Image modifiedprovideIcon = provideIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        provideIcon = new ImageIcon(modifiedprovideIcon);
        provide = new JLabel( provideIcon);

//        ImageIcon topImg = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\tools1.jpg");
//        Image topImgImage = topImg.getImage();
//        Image modifiedTopImg = topImgImage.getScaledInstance(10, 100, Image.SCALE_SMOOTH);
//        topImg = new ImageIcon(modifiedTopImg);
//        topImageLbl = new JLabel( topImg);

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
    }
}
