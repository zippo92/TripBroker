package Mvc.View;


import Mvc.Control.LogInControl;

import javax.swing.*;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInView {

        private  JLabel userLabel;
        private  JTextField userText;
        private  JLabel passwordLabel;
        private  JPasswordField passwordText;
        private  JButton loginButton;


    public LogInView()
    {
        JFrame frame = new JFrame("Trip Broker");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);

    }

    public void addController(LogInControl controller){
        System.out.println("View      : adding controller");
        loginButton.addActionListener(controller);
    } //addController()

    private  void placeComponents(JPanel panel) {

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);


    }

    public String getUsername()
    {
        return userText.getText().trim();

    }

}