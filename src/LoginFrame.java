import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LoginFrame extends JFrame {
    private Connection connection;  // Database connection

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Initialize database connection
        try {
            connection = DatabaseConnectionClass.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 50, 80, 25);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(180, 50, 120, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 100, 80, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(180, 100, 120, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 150, 80, 25);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Username and password cannot be empty!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                UserCredentials credentials = authenticateUser(username, password);
                if (credentials != null) {
                    // Successful login
                    if (credentials.isAdmin()) {
                        new AdminDashboardFrame();
                    } else {
                        new UserDashboardFrame();
                    }
                    dispose(); // Close the Login Frame
                } else {
                    // Failed login
                    JOptionPane.showMessageDialog(this,
                            "Invalid username or password!",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText(""); // Clear password field
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Database error occurred!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 150, 80, 25);
        registerButton.addActionListener(e -> {
            new RegistrationFrame();
            dispose();
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private UserCredentials authenticateUser(String username, String password)
            throws SQLException {
        String sql = "SELECT user_id, password_hash, salt, is_admin FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    String salt = rs.getString("salt");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    int userId = rs.getInt("user_id");

                    // Hash the provided password with the stored salt
                    String hashedPassword = hashPassword(password, salt);

                    // Compare the hashed password with stored hash
                    if (hashedPassword.equals(storedHash)) {
                        return new UserCredentials(userId, username, isAdmin);
                    }
                }
            }
        }
        return null;
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwordWithSalt = password + salt;
            byte[] hashedBytes = md.digest(passwordWithSalt.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class UserCredentials {
    private int userId;
    private String username;
    private boolean isAdmin;

    public UserCredentials(int userId, String username, boolean isAdmin) {
        this.userId = userId;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public boolean isAdmin() { return isAdmin; }
}

// Database connection helper class (you'll need to implement this)
class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // Implement your database connection logic here
        // Example:
        // return DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database",
        //     "username", "password");
        throw new SQLException("Database connection not implemented");
    }
}































//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LoginFrame extends JFrame {
//    public LoginFrame() {
//        setTitle("Login");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//
//        JLabel usernameLabel = new JLabel("Username:");
//        usernameLabel.setBounds(100, 50, 80, 25);
//        JTextField usernameField = new JTextField();
//        usernameField.setBounds(180, 50, 120, 25);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(100, 100, 80, 25);
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setBounds(180, 100, 120, 25);
//
//        JButton loginButton = new JButton("Login");
//        loginButton.setBounds(100, 150, 80, 25);
//        loginButton.addActionListener(e -> {
//            String username = usernameField.getText();
//            String password = new String(passwordField.getPassword());
//            // Placeholder role check; replace with actual authentication logic
//            if (username.equals("admin")) {
//                new AdminDashboardFrame();
//            } else {
//                new UserDashboardFrame();
//            }
//            dispose(); // Close the Login Frame
//        });
//
//        JButton registerButton = new JButton("Register");
//        registerButton.setBounds(200, 150, 80, 25);
//        registerButton.addActionListener(e -> {
//            new RegistrationFrame();
//            dispose();
//        });
//
//        add(usernameLabel);
//        add(usernameField);
//        add(passwordLabel);
//        add(passwordField);
//        add(loginButton);
//        add(registerButton);
//        setVisible(true);
//    }
//}
