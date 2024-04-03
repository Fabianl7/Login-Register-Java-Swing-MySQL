package screens;

import database.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class signUpScreenGui extends JFrame{
    JTextField usernameField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    public signUpScreenGui(){
        super("Sign Up Screen");
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
        JLabel titleLabel = new JLabel("Sign Up Screen");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(60, 10, 400, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        this.add(titleLabel);

        // Add Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setBounds(10, 45, 500, 50);
        usernameLabel.setHorizontalAlignment(JLabel.LEFT);
        usernameLabel.setForeground(Color.BLACK);
        this.add(usernameLabel);

        // Add Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setBounds(10, 150, 500, 50);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        passwordLabel.setForeground(Color.BLACK);
        this.add(passwordLabel);

        // Add Confirm Password Label
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPasswordLabel.setBounds(10, 265, 500, 50);
        confirmPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
        confirmPasswordLabel.setForeground(Color.BLACK);
        this.add(confirmPasswordLabel);

        // Add Username text input field
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 20));
        usernameField.setBounds(10, 90, 500, 50);
        usernameField.setBackground(Color.BLACK);
        usernameField.setForeground(new Color(139, 0, 0));
        this.add(usernameField);

        // Add Password text input field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        passwordField.setBounds(10, 195, 500, 50);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(new Color(139, 0, 0));
        this.add(passwordField);

        // Add Confirm Password text input field
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPasswordField.setBounds(10, 310, 500, 50);
        confirmPasswordField.setBackground(Color.BLACK);
        confirmPasswordField.setForeground(new Color(139, 0, 0));
        this.add(confirmPasswordField);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setBounds(150, 400, 200, 40);
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(new Color(139, 0, 0));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String confirmedPassword = confirmPasswordField.getText();

                // Sign Up Unsuccessful if input is empty or only has spaces
                if(!verifyInput()) {
                    JOptionPane.showMessageDialog(signUpScreenGui.this,
                            "Sign Up unsuccessful :( empty fields");
                    return;
                }

                // Verify if passwords match
                if(!password.equals(confirmedPassword)) {
                    JOptionPane.showMessageDialog(signUpScreenGui.this,
                            "Sign Up unsuccessful :( passwords don't match");
                    return;
                }

                // true - username doesn't exist in the database = success
                // false - username exists in the database = unsuccessful
                if(JDBC.verifyUsername(username)){
                    JDBC.insertUserSignUp(username, password);

                    JOptionPane.showMessageDialog(signUpScreenGui.this,
                            "Sign Up successful :)");
                }
                else
                    JOptionPane.showMessageDialog(signUpScreenGui.this,
                            "Sign Up unsuccessful :( Username already exists");

                usernameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
        });
        this.add(signUpButton);

        // Sign Up label
        JLabel signUpLabel = new JLabel("Go Back To Log In");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        signUpLabel.setBounds(5, 450, 500, 30);
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);
        signUpLabel.setForeground(new Color(139, 0, 0));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginScreenGui loginScreen = new loginScreenGui();
                loginScreen.setLocationRelativeTo(signUpScreenGui.this);

                signUpScreenGui.this.dispose();

                loginScreen.setVisible(true);
            }
        });
        this.add(signUpLabel);
    }

    private boolean verifyInput(){
        if(usernameField.getText().replaceAll(" ", "").length() == 0)
            return false;

        if(passwordField.getText().replaceAll(" ", "").length() == 0)
            return false;

        if(confirmPasswordField.getText().replaceAll(" ", "").length() == 0)
            return false;

        return true;
    }
}
