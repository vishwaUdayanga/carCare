package Views;

import Controllers.LoginController;
import Models.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class LoginView extends JFrame {
    public JPanel loginPanel;
    private JPanel gradientCover;
    private JLabel loginImg;
    private JTextField userName;
    private JTextField password;
    private JButton signInButton;


    public LoginView() {

        userName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userName.getText().equals("User Name")) {
                    userName.setText("");
                }
            }
        });
        userName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (userName.getText().isEmpty()) {
                    userName.setText("User Name");
                }
            }
        });
        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (password.getText().equals("Password")) {
                    password.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (password.getText().isEmpty()) {
                    password.setText("Password");
                }
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String admin=userName.getText();
                String pass=password.getText();
                Admin u=new Admin(admin,pass,null);
                LoginController controller=new LoginController();
                Admin valid = controller.validateUser(u);

                if (valid!=null) {
                    LoginView.super.dispose();
                    DashboardView dashboardView = new DashboardView();
                    dashboardView.setContentPane(dashboardView.dashboardPanel);
                    dashboardView.setTitle("Dashboard");
                    dashboardView.setSize(800, 500);
                    dashboardView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dashboardView.setVisible(true);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    dashboardView.setLocation(dim.width/2-dashboardView.getSize().width/2, dim.height/2-dashboardView.getSize().height/2);
                } else {
                    JOptionPane.showMessageDialog(loginPanel, "Incorrect username or password", "Error", 0);
                    userName.setText("User Name");
                    password.setText("Password");
                }
            }
        });
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.setContentPane(loginView.loginPanel);
        loginView.setTitle("Login View");
        loginView.setSize(800, 500);
        loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginView.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        loginView.setLocation(dim.width/2-loginView.getSize().width/2, dim.height/2-loginView.getSize().height/2);
    }

    private void createUIComponents() {
        gradientCover = new javax.swing.JPanel() {
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

        ImageIcon icon = new ImageIcon("E:\\Education\\Year 1 sem 2\\OOP\\Group Project\\carCare\\Project\\src\\main\\java\\Views\\Images\\login.jpg");
        Image iconImage = icon.getImage();
        Image modified = iconImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        icon = new ImageIcon(modified);
        loginImg = new JLabel( icon);

        userName = new LoginView.RoundedJTextField(20);
        userName.setText("User Name");

        password = new LoginView.RoundedJTextField(20);
        password.setText("Password");
    }

    // implement a round-shaped JTextField
    static class RoundedJTextField extends JTextField {
        private Shape shape;
        public RoundedJTextField(int size) {
            super(size);
            setBounds(520, 284, 190, 25);
            setBackground(new Color(0, 0, 0, 0));
            setForeground(new Color(255, 255, 255, 255));
            setOpaque(false);
        }
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(0, 0, 0, 0));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(new Color(255, 255, 255, 255));
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
