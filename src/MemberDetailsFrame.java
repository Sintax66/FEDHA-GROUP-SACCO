import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDetailsFrame extends JFrame {
    public MemberDetailsFrame() {
        setTitle("Member Details");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel detailsLabel = new JLabel("Member Details");
        detailsLabel.setBounds(150, 20, 100, 25);

        JTextArea memberDetailsArea = new JTextArea();
        memberDetailsArea.setBounds(50, 60, 300, 200);
        memberDetailsArea.setEditable(false);
        // Placeholder text - Replace with actual member details from the database
        memberDetailsArea.setText("Member details will be displayed here.");

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 300, 100, 25);
        backButton.addActionListener(e -> {
            // Navigate back based on role
            new UserDashboardFrame(); // Or AdminDashboardFrame, depending on context
            dispose();
        });

        add(detailsLabel);
        add(memberDetailsArea);
        add(backButton);
        setVisible(true);
    }
}
