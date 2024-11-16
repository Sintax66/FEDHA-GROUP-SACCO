import javax.swing.*;
import java.lang.reflect.Member;

public class LoanApplicationFrame extends JFrame {

    private Member memberInstance; // Add this field

    public LoanApplicationFrame(Member member) {
        setTitle("Loan Application");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        this.memberInstance = member; // Store the member
        setTitle("Loan Application");

        JLabel loanTypeLabel = new JLabel("Loan Type:");
        loanTypeLabel.setBounds(100, 50, 80, 25);
        String[] loanTypes = {"Emergency Loan", "Short Loan", "Normal Loan", "Development Loan"};
        JComboBox<String> loanTypeBox = new JComboBox<>(loanTypes);
        loanTypeBox.setBounds(180, 50, 120, 25);

        JLabel amountLabel = new JLabel("Loan Amount:");
        amountLabel.setBounds(100, 100, 80, 25);
        JTextField amountField = new JTextField();
        amountField.setBounds(180, 100, 120, 25);

        JLabel guarantorsLabel = new JLabel("Guarantors:");
        guarantorsLabel.setBounds(100, 150, 80, 25);
        JTextField guarantorsField = new JTextField();
        guarantorsField.setBounds(180, 150, 120, 25);

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(150, 200, 100, 25);
        applyButton.addActionListener(e -> {
//            String loanType = (String) loanTypeBox.getSelectedItem();
//            String amount = amountField.getText();
//            String guarantors = guarantorsField.getText();
//            double loanAmount = Double.parseDouble(amountField.getText());
//            String result = LoanApproval.applyForLoan(currentMember, loanAmount);
//            JOptionPane.showMessageDialog(this, result);
            // Loan application logic
//            JOptionPane.showMessageDialog(this, "Loan applied for: " + loanType + " with amount " + amount);
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 250, 100, 25);
        backButton.addActionListener(e -> {
            new UserDashboardFrame();
            dispose();
        });

//        // Inside Loan Application Frame
//        applyButton.addActionListener(e -> {
//            double loanAmount = Double.parseDouble(amountField.getText());
//            String result = LoanApproval.applyForLoan(currentMember, loanAmount);
//            JOptionPane.showMessageDialog(this, result);
//        });

         class LoanApproval {
            // Check if the member is eligible for a loan (contributed for at least 6 months)
            public static boolean isEligibleForLoan(Member member) {
                return member.getMonthsContributed() >= 6;
            }

            // Calculate the maximum loan a member is eligible for based on their shares
            public static double calculateMaxLoan(Member member) {
                double shares = member.getShares();
                double maxLoan = 0;

                switch (member.getLoanType()) {
                    case "Emergency Loan":
                        maxLoan = shares;
                        break;
                    case "Short Loan":
                        maxLoan = shares * 2;
                        break;
                    case "Normal Loan":
                        maxLoan = shares * 3;
                        break;
                    case "Development Loan":
                        maxLoan = shares * 5;
                        break;
                }
                return maxLoan;
            }

            // Logic to process loan application
            public static String applyForLoan(Member member, double requestedLoanAmount) {
                if (!isEligibleForLoan(member)) {
                    return "Member is not eligible for a loan.";
                }

                double maxLoan = calculateMaxLoan(member);

                if (requestedLoanAmount > maxLoan) {
                    return "Loan amount exceeds the maximum eligible amount of " + maxLoan;
                }

                // Proceed to loan approval logic here (e.g., saving loan record)
                return "Loan application successful!";
            }
        }

        // Inside Loan Application Frame
        applyButton.addActionListener(e -> {
            String loanType = (String) loanTypeBox.getSelectedItem();
            String amount = amountField.getText();
            String guarantors = guarantorsField.getText();
            double loanAmount = Double.parseDouble(amountField.getText());
            String result = LoanApproval.applyForLoan(memberInstance, loanAmount);
            JOptionPane.showMessageDialog(this, result);
            JOptionPane.showMessageDialog(this, "Loan applied for: " + loanType + " with amount " + amount);
        });

        add(loanTypeLabel);
        add(loanTypeBox);
        add(amountLabel);
        add(amountField);
        add(guarantorsLabel);
        add(guarantorsField);
        add(applyButton);
        add(backButton);
        setVisible(true);
    }
    public class Member {
        private String firstName;
        private String lastName;
        private double shares;
        private int monthsContributed;
        private String loanType;  // Loan type (Emergency Loan, Short Loan, etc.)
        private double outstandingLoan;
        private double totalContributions;

        // Constructor
        public Member(String firstName, String lastName, double shares, int monthsContributed, String loanType) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.shares = shares;
            this.monthsContributed = monthsContributed;
            this.loanType = loanType;
            this.outstandingLoan = 0;
            this.totalContributions = 0;
        }

        // Getters and Setters
        public double getShares() {
            return shares;
        }

        public void setShares(double shares) {
            this.shares = shares;
        }

        public int getMonthsContributed() {
            return monthsContributed;
        }

        public void setMonthsContributed(int monthsContributed) {
            this.monthsContributed = monthsContributed;
        }

        public String getLoanType() {
            return loanType;
        }

        public void setLoanType(String loanType) {
            this.loanType = loanType;
        }

        public double getOutstandingLoan() {
            return outstandingLoan;
        }

        public void setOutstandingLoan(double outstandingLoan) {
            this.outstandingLoan = outstandingLoan;
        }

        public double getTotalContributions() {
            return totalContributions;
        }

        public void addContribution(double contribution) {
            this.totalContributions += contribution;
            this.monthsContributed++;
        }

        // You can add other fields and methods as necessary
    }
}
