package screens;

import database.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginScreenGui extends JFrame {
    public loginScreenGui(){
        super("Login Screen");
        setSize(550, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);

        addGuiComponents();
    }

    private void addGuiComponents(){
        // Add Title
        JLabel titleLabel = new JLabel("Login Screen");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(60, 10, 400, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        this.add(titleLabel);

        // Add Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setBounds(10, 135, 500, 50);
        usernameLabel.setHorizontalAlignment(JLabel.LEFT);
        usernameLabel.setForeground(Color.BLACK);
        this.add(usernameLabel);

        // Add Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setBounds(10, 260, 500, 50);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        passwordLabel.setForeground(Color.BLACK);
        this.add(passwordLabel);

        // Add Username text input field
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 20));
        usernameField.setBounds(10, 180, 500, 50);
        usernameField.setBackground(Color.BLACK);
        usernameField.setForeground(new Color(139, 0, 0));
        this.add(usernameField);

        // Add Password text input field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        passwordField.setBounds(10, 305, 500, 50);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(new Color(139, 0, 0));
        this.add(passwordField);

        // Log In Button
        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBounds(150, 400, 200, 40);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(new Color(139, 0, 0));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if(JDBC.verifyUserLogIn(username, password)){
                    JOptionPane.showMessageDialog(loginScreenGui.this,
                            "Successfully Logged In :)");
                } else{
                    JOptionPane.showMessageDialog(loginScreenGui.this,
                            "Log In Unsuccessful :(");
                }
            }
        });
        this.add(loginButton);

        // Sign Up label
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        signUpLabel.setBounds(10, 460, 500, 30);
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);
        signUpLabel.setForeground(new Color(139, 0, 0));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpScreenGui signupScreen = new signUpScreenGui();
                signupScreen.setLocationRelativeTo(loginScreenGui.this);

                loginScreenGui.this.dispose();

                signupScreen.setVisible(true);
            }
        });
        this.add(signUpLabel);
    }
}
