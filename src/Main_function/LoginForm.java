package Main_function;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Exceptions.UserIdNotFound;
import Exceptions.UserPswdNotFound;
import services.UserLogin;
import dao.users.Users;


public class LoginForm extends JFrame {

    //Creates a font of Times New Roman
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);

    //Creates an input field for the email and the password
    JTextField tfEmail;
    JPasswordField pfPassword;
    
    //method to create the display
    public void initialize(){

        //Created a new label for the Log in form
        JLabel lbloginForm = new JLabel("Login Form", SwingConstants.CENTER);
        lbloginForm.setFont(mainFont);

        //Created a new label for the email input
        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        //Sets the input field for the email
        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        //Creates a label for the password
        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        //Sets the password field
        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);


        //Creates a panel to add the information above to
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));

        //Adds the email and password information
        formPanel.add(lbloginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        //Creates a submit button that gets the email information and the password
        //and calls User Login serice to authenticate the user. Also uses the 
        //exceptions useridnotfound and userpswdnot found to catch incorrect log ins
        //Takes authenticated user to the main display
        JButton btnLogin = new JButton("Signin");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                Users currUser;
                try {
                    currUser = UserLogin.login(email, password);
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
                } catch (UserIdNotFound | UserPswdNotFound e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                
            
            }

        });

        //Creates a cancel button that ends the program
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
            }
            
        });

        //Creates a panel for the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));

        //Adds the buttons
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);

        //Organizes the panels
        add(formPanel,BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        //Creates the whole display
        setTitle("Sign In!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
