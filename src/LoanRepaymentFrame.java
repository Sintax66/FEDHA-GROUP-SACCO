import javax.swing.*;

// First, create the Loan class
class Loan {
    private double amount;
    private String loanType;
    private int termInMonths;

    public Loan(double amount, String loanType, int termInMonths) {
        this.amount = amount;
        this.loanType = loanType;
        this.termInMonths = termInMonths;
    }

    public double getAmount() {
        return amount;
    }

    public String getLoanType() {
        return loanType;
    }

    public int getTermInMonths() {
        return termInMonths;
    }
}



// The main LoanRepaymentFrame class
public class LoanRepaymentFrame extends JFrame {
    private Loan currentLoan;
    private JTextField monthlyRepaymentField;

    public LoanRepaymentFrame(Loan loan) {
        this.currentLoan = loan;

        setTitle("Loan Repayment");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Outstanding Loans Label
        JLabel outstandingLabel = new JLabel("Outstanding Loans:");
        outstandingLabel.setBounds(100, 50, 200, 25);

        // Loan Details Area
        JTextArea loanArea = new JTextArea();
        loanArea.setBounds(50, 80, 300, 100);
        loanArea.setEditable(false);
        if (currentLoan != null) {
            loanArea.setText(String.format("Loan Type: %s%nAmount: $%.2f%nTerm: %d months",
                    currentLoan.getLoanType(),
                    currentLoan.getAmount(),
                    currentLoan.getTermInMonths()));
        } else {
            loanArea.setText("No outstanding loans.");
        }

        // Monthly Repayment Section
        JLabel monthlyRepaymentLabel = new JLabel("Monthly Repayment:");
        monthlyRepaymentLabel.setBounds(100, 170, 120, 25);
        monthlyRepaymentField = new JTextField();
        monthlyRepaymentField.setBounds(230, 170, 100, 25);
        monthlyRepaymentField.setEditable(false);

        // Repayment Amount Section
        JLabel repayAmountLabel = new JLabel("Repayment Amount:");
        repayAmountLabel.setBounds(100, 200, 120, 25);
        JTextField repayAmountField = new JTextField();
        repayAmountField.setBounds(230, 200, 100, 25);

        // Repay Button
        JButton repayButton = new JButton("Repay");
        repayButton.setBounds(150, 250, 100, 25);
        repayButton.addActionListener(e -> {
            try {
                double repayAmount = Double.parseDouble(repayAmountField.getText());
                if (currentLoan != null && repayAmount > 0) {
                    // Add your repayment processing logic here
                    JOptionPane.showMessageDialog(this,
                            String.format("Repayment of $%.2f processed.", repayAmount));
                    repayAmountField.setText("");
                    updateLoanDetails(); // Update the display after repayment
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Please enter a valid repayment amount.",
                            "Invalid Amount",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 300, 100, 25);
        backButton.addActionListener(e -> {
            new UserDashboardFrame();
            dispose();
        });

        // Calculate and display monthly repayment
        if (currentLoan != null) {
            double loanInterest = InterestCalculation.calculateLoanInterest(
                    currentLoan.getAmount(),
                    currentLoan.getLoanType(),
                    currentLoan.getTermInMonths()
            );
            double totalAmountDue = currentLoan.getAmount() + loanInterest;
            monthlyRepaymentField.setText(String.format("%.2f",
                    totalAmountDue / currentLoan.getTermInMonths()));
        }

        // Add all components to the frame
        add(outstandingLabel);
        add(loanArea);
        add(monthlyRepaymentLabel);
        add(monthlyRepaymentField);
        add(repayAmountLabel);
        add(repayAmountField);
        add(repayButton);
        add(backButton);

        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    // Interest calculation class
    static class InterestCalculation {
        public static double calculateLoanInterest(double loanAmount, String loanType, int months) {
            double interestRate = 0;
            switch (loanType) {
                case "Emergency Loan":
                    interestRate = 0.3;
                    break;
                case "Short Loan":
                    interestRate = 0.6;
                    break;
                case "Normal Loan":
                    interestRate = 1.0;
                    break;
                case "Development Loan":
                    interestRate = 1.4;
                    break;
            }
            return loanAmount * interestRate * months / 100;
        }

        public static double calculateFixedDepositInterest(double depositAmount, int months) {
            return depositAmount * 0.6 * months / 100;
        }
    }

    // Helper method to update loan details after repayment
    private void updateLoanDetails() {
        if (currentLoan != null) {
            double loanInterest = InterestCalculation.calculateLoanInterest(
                    currentLoan.getAmount(),
                    currentLoan.getLoanType(),
                    currentLoan.getTermInMonths()
            );
            double totalAmountDue = currentLoan.getAmount() + loanInterest;
            monthlyRepaymentField.setText(String.format("%.2f",
                    totalAmountDue / currentLoan.getTermInMonths()));
        }
    }

    // Example of how to create and use this frame
    public static void main(String[] args) {
        // Example usage
        SwingUtilities.invokeLater(() -> {
            Loan sampleLoan = new Loan(10000.0, "Normal Loan", 12);
            new LoanRepaymentFrame(sampleLoan);
        });
    }
}

























//import javax.swing.*;
//
//public class LoanRepaymentFrame extends JFrame {
//    public LoanRepaymentFrame() {
//        setTitle("Loan Repayment");
//        setSize(400, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//
//        JLabel outstandingLabel = new JLabel("Outstanding Loans:");
//        outstandingLabel.setBounds(100, 50, 200, 25);
//
//        JTextArea loanArea = new JTextArea();
//        loanArea.setBounds(50, 80, 300, 100);
//        loanArea.setEditable(false);
//        loanArea.setText("Outstanding loans will be listed here.");
//
//        JLabel repayAmountLabel = new JLabel("Repayment Amount:");
//        repayAmountLabel.setBounds(100, 200, 120, 25);
//        JTextField repayAmountField = new JTextField();
//        repayAmountField.setBounds(230, 200, 100, 25);
//
//        JButton repayButton = new JButton("Repay");
//        repayButton.setBounds(150, 250, 100, 25);
//        repayButton.addActionListener(e -> {
//            String repayAmount = repayAmountField.getText();
//            // Repayment logic
//            JOptionPane.showMessageDialog(this, "Repayment of " + repayAmount + " processed.");
//            repayAmountField.setText("");
//        });
//
//        JButton backButton = new JButton("Back");
//        backButton.setBounds(150, 300, 100, 25);
//        backButton.addActionListener(e -> {
//            new UserDashboardFrame();
//            dispose();
//        });
//
//         class InterestCalculation {
//            // Calculate loan interest based on loan type and loan amount
//            public static double calculateLoanInterest(double loanAmount, String loanType, int months) {
//                double interestRate = 0;
//                switch (loanType) {
//                    case "Emergency Loan":
//                        interestRate = 0.3;
//                        break;
//                    case "Short Loan":
//                        interestRate = 0.6;
//                        break;
//                    case "Normal Loan":
//                        interestRate = 1.0;
//                        break;
//                    case "Development Loan":
//                        interestRate = 1.4;
//                        break;
//                }
//                return loanAmount * interestRate * months / 100;
//            }
//
//            // Calculate interest for fixed deposits (savings not borrowed)
//            public static double calculateFixedDepositInterest(double depositAmount, int months) {
//                return depositAmount * 0.6 * months / 100;
//            }
//        }
//
//        // Inside Loan Repayment Frame
//        double loanInterest = InterestCalculation.calculateLoanInterest(currentLoan.getAmount(), currentLoan.getLoanType(), loanTermInMonths);
//        double totalAmountDue = currentLoan.getAmount() + loanInterest;
//        monthlyRepaymentField.setText(String.valueOf(totalAmountDue / loanTermInMonths));
//
//        add(outstandingLabel);
//        add(loanArea);
//        add(repayAmountLabel);
//        add(repayAmountField);
//        add(repayButton);
//        add(backButton);
//        setVisible(true);
//    }
//}
