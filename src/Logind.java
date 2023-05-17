import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logind {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Swing Example");

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // Creating login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        // Creating welcome label
        JLabel welcomeLabel = new JLabel("");
        welcomeLabel.setBounds(10, 110, 300, 25);
        panel.add(welcomeLabel);

        JLabel success = new JLabel("");
        success.setBounds(10, 140, 300, 25);
        panel.add(success);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (username.length() > 6 && password.length() > 6) {
                    success.setText("Login successful");
                    welcomeLabel.setText("Welcome to our memory game, " + username);

                    // Open new window
                    if (e.getSource() == loginButton) {
                        Window window = new Window();
                        window.setVisible(true); // Show the window
                        
                    }
                } else {
                    success.setText("username or password length should be more than 5");
                    welcomeLabel.setText("");
                }
            }
        });
    }
}
