package Main_function;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import services.UserLogin;
import dao.users.Users;


public class LoginForm extends JFrame {

    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;
    
    public void initialize(){
        JLabel lbloginForm = new JLabel("Login Form", SwingConstants.CENTER);
        lbloginForm.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);



        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));

        formPanel.add(lbloginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        JButton btnLogin = new JButton("Signin");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                System.out.println(email + " " + password);

                Users currUser = UserLogin.login(email, password);

                if(currUser != null){
                    //change curr user or create new user based of userBookInfo
                    mainFrame userList = new mainFrame();
                    userList.initialize(currUser);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            
            }

        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);

        add(formPanel,BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
        setTitle("Sign In!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
