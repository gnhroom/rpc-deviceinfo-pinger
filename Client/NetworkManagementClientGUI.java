import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class NetworkManagementClientGUI {

    private static NetworkManagement networkManagement;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(NetworkManagementClientGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Network Management Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        JLabel ipAddressLabel = new JLabel("IP Address:");
        JTextField ipAddressField = new JTextField(15);
        JButton getInfoButton = new JButton("Get Device Info");

        JTextArea resultArea = new JTextArea(40, 50);
        resultArea.setEditable(false);

        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddress = ipAddressField.getText();
                String result = getDeviceInfo(ipAddress);
                resultArea.setText(result);
            }
        });

        inputPanel.add(ipAddressLabel);
        inputPanel.add(ipAddressField);
        inputPanel.add(getInfoButton);

        resultPanel.add(new JScrollPane(resultArea));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(resultPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private static String getDeviceInfo(String ipAddress) {
        try {
            networkManagement = (NetworkManagement) Naming.lookup("rmi://localhost/NetworkManagementService");
            return networkManagement.getDeviceInfo(ipAddress);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
