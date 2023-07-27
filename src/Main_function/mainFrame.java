package Main_function;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;


import dao.users.Users;

public class mainFrame extends JFrame{

    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    
    public void initialize(Users currUser) {
    
    JLabel title = new JLabel("Your Books", SwingConstants.CENTER);
    title.setFont(mainFont);

    JLabel inProgress = new JLabel("In Progress:");
    inProgress.setFont(mainFont);

    JLabel completed = new JLabel("Completed: ");
    completed.setFont(mainFont);

    JLabel notStarted = new JLabel("Plan to Read:");
    notStarted.setFont(mainFont);

    JButton btnLogout = new JButton("Log Out");
    btnLogout.setFont(mainFont);
    
    JButton btnUpdate = new JButton("Update Book");
    btnUpdate.setFont(mainFont);

    JButton btnAdd = new JButton("Add Book");
    btnAdd.setFont(mainFont);

    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new GridLayout(0,1,5,5));

    JPanel BookPanel = new JPanel();
    BookPanel.setLayout(new GridLayout(0,1,5,5));

    JPanel BtnPanel = new JPanel();
    BookPanel.setLayout(new GridLayout(0,1,5,5));


    titlePanel.add(title);
    BookPanel.add(inProgress);
    BookPanel.add(notStarted);
    BookPanel.add(completed);
    BtnPanel.add(btnUpdate);
    BtnPanel.add(btnAdd);
    BtnPanel.add(btnLogout);


    add(titlePanel, BorderLayout.NORTH);
    add(BookPanel, BorderLayout.CENTER);
    add(BtnPanel, BorderLayout.SOUTH);

    setTitle("Dashboard");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(650,650);
    setLocationRelativeTo(null);
    setVisible(true);
    }

}
