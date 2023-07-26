package Main_function;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;


import dao.users.Users;

public class mainFrame extends JFrame{

    public void initialize(Users currUser) {
    

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new GridLayout(0,2,5,5));

    add(infoPanel, BorderLayout.NORTH);


    setTitle("Dashboard");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(100,650);
    setLocationRelativeTo(null);
    setVisible(true);
    }

}
