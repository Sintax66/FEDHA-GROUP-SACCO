import javax.swing.*;

public class AdminDashboardFrame extends JFrame {
    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton manageMembersButton = new JButton("Manage Members");
        manageMembersButton.setBounds(100, 50, 200, 25);
        manageMembersButton.addActionListener(e -> {
            new MemberDetailsFrame(); // Add admin-specific controls here
            dispose();
        });

        JButton viewReportsButton = new JButton("Generate Reports");
        viewReportsButton.setBounds(100, 100, 200, 25);
        viewReportsButton.addActionListener(e -> {
            new ReportsFrame();
            dispose();
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 150, 200, 25);
        backButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        add(manageMembersButton);
        add(viewReportsButton);
        add(backButton);
        setVisible(true);
    }
}
