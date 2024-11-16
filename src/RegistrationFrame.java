import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationFrame extends JFrame {

    private Connection connection;  // Database connection
    private JTextField firstNameField, middleNameField, lastNameField, idNumberField, emailField, phoneNumberField, occupationField;

    public RegistrationFrame() {



        setTitle("Registration");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(11, 2, 10, 10));

        // Initialize database connection
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

//        JLabel nameLabel = new JLabel("Name:");
//        nameLabel.setBounds(100, 50, 80, 25);
//        JTextField nameField = new JTextField();
//        nameField.setBounds(180, 50, 120, 25);

        // Labels and input fields
        this.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        this.add(firstNameField);

        this.add(new JLabel("Middle Name:"));
        middleNameField = new JTextField();
        this.add(middleNameField);

        this.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        this.add(lastNameField);

        this.add(new JLabel("ID Number:"));
        idNumberField = new JTextField();
        this.add(idNumberField);

        this.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        this.add(emailField);

        this.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        this.add(phoneNumberField);


        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(100, 100, 80, 25);
        String[] roles = {"User", "Admin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setBounds(180, 100, 120, 25);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 150, 80, 25);
        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String idNumber = idNumberField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String role = roleBox.getSelectedItem().toString();
            // Save user to database
            new LoginFrame();
            dispose();
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 150, 80, 25);
        backButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

//        add(nameLabel);
//        add(nameField);
        add(roleLabel);
        add(roleBox);
        add(registerButton);
        add(backButton);
        setVisible(true);
    }
}
