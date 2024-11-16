import javax.swing.*;

public class ReportsFrame extends JFrame {

    public class DividendsLogic {
        // Calculate total revenue from loans and fixed deposits
        public static double calculateTotalRevenue(double loanInterest, double fixedDepositInterest) {
            return loanInterest + fixedDepositInterest;
        }

        // Calculate dividends for each member based on their share contribution
        public static double calculateDividends(double totalRevenue, LoanApplicationFrame.Member member, double totalShares) {
            double sharePercentage = member.getShares() / totalShares;
            return totalRevenue * sharePercentage;
        }
    }

    public ReportsFrame() {
        setTitle("Reports");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel reportLabel = new JLabel("Generate Report:");
        reportLabel.setBounds(100, 50, 100, 25);

        // Add dividends label to display dividends calculation
        JLabel dividendsLabel = new JLabel();
        dividendsLabel.setBounds(100, 250, 200, 25);

        String[] reports = {"Member Registration Fees", "Share Contributions", "Fixed Deposits", "Loans Borrowed"};
        JComboBox<String> reportBox = new JComboBox<>(reports);
        reportBox.setBounds(100, 100, 200, 25);

        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(150, 150, 100, 25);
        generateButton.addActionListener(e -> {
            String reportType = (String) reportBox.getSelectedItem();
            // Logic to generate report
            JOptionPane.showMessageDialog(this, "Generated report: " + reportType);
        });

        // Define placeholders for required variables
        double totalLoanInterest = 0.0; // Set an initial value
        double totalFixedDepositInterest = 0.0; // Set an initial value
        LoanApplicationFrame.Member currentMember = null; // Define or fetch the actual member
        double totalShares = 1.0; // Set an initial value to avoid division by zero




        // Inside Reports Frame
        double totalRevenue = DividendsLogic.calculateTotalRevenue(totalLoanInterest, totalFixedDepositInterest);
        if (currentMember != null) {
            double memberDividends = DividendsLogic.calculateDividends(totalRevenue, currentMember, totalShares);
            dividendsLabel.setText("Dividends: " + memberDividends);
        } else {
            dividendsLabel.setText("Dividends: N/A");
        }

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 200, 100, 25);
        backButton.addActionListener(e -> {
            new AdminDashboardFrame();
            dispose();
        });

        add(dividendsLabel);
        add(reportLabel);
        add(reportBox);
        add(generateButton);
        add(backButton);
        setVisible(true);
    }
}