import javax.swing.*;

public class ContributionsFrame extends JFrame {

    private LoanApplicationFrame.Member currentMember; // Add this field

    // Nested class for tracking contributions
    class ContributionsTracking {
        // Track monthly contributions of a member
        public static void trackContribution(LoanApplicationFrame.Member member, double contributionAmount) {
            member.addContribution(contributionAmount);
            member.setMonthsContributed(member.getMonthsContributed() + 1);

            if (member.getMonthsContributed() >= 6) {
                // Allow loan application after 6 months of contributions
                System.out.println("Member is eligible for a loan after 6 months of contributions.");
            }
        }
    }



    public ContributionsFrame(LoanApplicationFrame.Member member) {
        setTitle("Contributions");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Initialize currentMember with the member argument
        this.currentMember = member;

        JLabel contributionLabel = new JLabel("Contribute Shares");
        contributionLabel.setBounds(150, 20, 100, 25);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(100, 70, 80, 25);
        JTextField amountField = new JTextField();
        amountField.setBounds(180, 70, 120, 25);

        JButton contributeButton = new JButton("Contribute");
        contributeButton.setBounds(150, 120, 100, 25);
        contributeButton.addActionListener(e -> {
            String amount = amountField.getText();
            try {
                // Parse contribution amount and track it
                double contribution = Double.parseDouble(amount);
                ContributionsTracking.trackContribution(currentMember, contribution);
                JOptionPane.showMessageDialog(this, "Contribution of " + amount + " made!");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for the contribution amount.");
            }

            // Add contribution logic here
            double contribution = Double.parseDouble(amountField.getText());
            ContributionsTracking.trackContribution(currentMember, contribution);
            JOptionPane.showMessageDialog(this, "Contribution of " + amount + " made!");
            amountField.setText("");
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 180, 100, 25);
        backButton.addActionListener(e -> {
            new UserDashboardFrame();
            dispose();
        });

         class ContributionsTracking {
            // Track monthly contributions of a member
            public static void trackContribution(LoanApplicationFrame.Member member, double contributionAmount) {
                member.addContribution(contributionAmount);
                member.setMonthsContributed(member.getMonthsContributed() + 1);

                if (member.getMonthsContributed() >= 6) {
                    // Allow loan application after 6 months of contributions
                    System.out.println("Member is eligible for a loan after 6 months of contributions.");
                }
            }
        }

        add(contributionLabel);
        add(amountLabel);
        add(amountField);
        add(contributeButton);
        add(backButton);
        setVisible(true);
    }
}
