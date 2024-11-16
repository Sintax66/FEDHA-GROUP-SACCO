import javax.swing.*;

public class UserDashboardFrame extends JFrame {

    private LoanApplicationFrame.Member currentMember;
    private ExitRequestFrame.Member currentmember;


    public UserDashboardFrame() {
        setTitle("User Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton memberDetailsButton = new JButton("View Member Details");
        memberDetailsButton.setBounds(100, 50, 200, 25);
        memberDetailsButton.addActionListener(e -> {
            new MemberDetailsFrame();
            dispose();
        });

        JButton contributionsButton = new JButton("Contributions");
        contributionsButton.setBounds(100, 100, 200, 25);
        contributionsButton.addActionListener(e -> {
            new ContributionsFrame(currentMember);
            dispose();
        });

        JButton loanApplicationButton = new JButton("Apply for Loan");
        loanApplicationButton.setBounds(100, 150, 200, 25);
        loanApplicationButton.addActionListener(e -> {
            new LoanApplicationFrame(currentMember);
            dispose();
        });

        JButton loanRepaymentButton = new JButton("Loan Repayment");
        loanRepaymentButton.setBounds(100, 200, 200, 25);
        loanRepaymentButton.addActionListener(e -> {
            // Create a temporary loan object for testing
            Loan currentLoan = new Loan(10000.0, "Normal Loan", 12);
            new LoanRepaymentFrame(currentLoan);
            dispose();
        });

        JButton exitRequestButton = new JButton("Exit Request");
        exitRequestButton.setBounds(100, 250, 200, 25);
        exitRequestButton.addActionListener(e -> {
            new ExitRequestFrame(currentmember);
            dispose();
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 300, 200, 25);
        backButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        add(memberDetailsButton);
        add(contributionsButton);
        add(loanApplicationButton);
        add(loanRepaymentButton);
        add(exitRequestButton);
        add(backButton);
        setVisible(true);
    }
}