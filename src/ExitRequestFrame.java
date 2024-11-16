import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExitRequestFrame extends JFrame {

    public static class Member {
        private List<String> emergencyLoans;
        private List<String> shortLoans;
        private List<String> normalLoans;
        private List<String> developmentLoans;
        private String exitStatus;

        public Member() {
            // Initialize loan lists
            emergencyLoans = new ArrayList<>();
            shortLoans = new ArrayList<>();
            normalLoans = new ArrayList<>();
            developmentLoans = new ArrayList<>();
        }

        public List<String> getEmergencyLoans() {
            return emergencyLoans;
        }

        public List<String> getShortLoans() {
            return shortLoans;
        }

        public List<String> getNormalLoans() {
            return normalLoans;
        }

        public List<String> getDevelopmentLoans() {
            return developmentLoans;
        }

        public boolean hasOutstandingLoans() {
            return !emergencyLoans.isEmpty() || !shortLoans.isEmpty() ||
                    !normalLoans.isEmpty() || !developmentLoans.isEmpty();
        }

        public void setExitStatus(String status) {
            this.exitStatus = status;
        }

        public String getExitStatus() {
            return exitStatus;
        }
    }

    class ExitRequestLogic {
        public static boolean canExit(ExitRequestFrame.Member member) {
            return !member.hasOutstandingLoans();
        }

        public static String processExitRequest(ExitRequestFrame.Member member) {
            if (canExit(member)) {
                member.setExitStatus("Approved");
                return "Exit request approved. Reimbursement will be processed.";
            } else {
                return "Exit request denied. Outstanding loans must be cleared.";
            }
        }
    }


//    public class ExitRequestLogic {
//        // Check if a member has repaid all loans and guaranteed loans
//        public static boolean canExit(LoanApplicationFrame.Member member) {
//            return member.getOutstandingLoans().isEmpty() && member.getGuaranteedLoans().isEmpty();
//        }
//
//        // Process the exit request
//        public static String processExitRequest(LoanApplicationFrame.Member member) {
//            if (canExit(member)) {
//                member.setExitStatus("Approved");
//                return "Exit request approved. Reimbursement will be processed.";
//            } else {
//                return "Exit request denied. Outstanding loans or guaranteed loans must be cleared.";
//            }
//        }
//    }

    private ExitRequestFrame.Member currentMember;

    public ExitRequestFrame(ExitRequestFrame.Member member) {
        setTitle("Exit Request");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel exitLabel = new JLabel("Request Exit:");
        exitLabel.setBounds(150, 50, 100, 25);

        JTextArea exitInfo = new JTextArea();
        exitInfo.setBounds(50, 80, 300, 200);
        exitInfo.setEditable(false);
        exitInfo.setText("Exit requirements will be displayed here.");

        JButton requestExitButton = new JButton("Request Exit");
        requestExitButton.setBounds(150, 300, 100, 25);
        requestExitButton.addActionListener(e -> {
            // Exit logic
            String result = ExitRequestLogic.processExitRequest(currentMember);
            JOptionPane.showMessageDialog(this, result);
            JOptionPane.showMessageDialog(this, "Exit request submitted.");
        });



        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 350, 100, 25);
        backButton.addActionListener(e -> {
            new UserDashboardFrame();
            dispose();
        });

        add(exitLabel);
        add(exitInfo);
        add(requestExitButton);
        add(backButton);
        setVisible(true);
    }
}