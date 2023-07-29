package Main_function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.books.Books;
import dao.users.Users;
import services.MasterTable;
import services.UserBooks;
import services.UserLogin;

public class mainFrame extends JFrame{

    
	final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
	final private Font titleFont = new Font("Times new roman", Font.BOLD, 20);
    final private Font attributeFont = new Font("Times new roman", Font.BOLD, 16);
    final private Font dataFont = new Font("Times new roman", Font.BOLD, 16);
   
    
    
    
    public void initialize(Users currUser) {
    	
    Books bookString = new Books();
    
    JLabel title = new JLabel(currUser.getFirst_name() + "'s Book Library", SwingConstants.CENTER);
    title.setFont(titleFont);
    title.setForeground(Color.BLUE);

    JLabel inProgress = new JLabel("In Progress:");
    inProgress.setFont(titleFont);
    
    //progress book list
    System.out.println("before first list created");
    List<MasterTable> inProgressBooks;
	inProgressBooks = UserBooks.getProgress(currUser.getUser_id());
    System.out.println("after first list created");


    JLabel completed = new JLabel("Completed: ");
    completed.setFont(titleFont);

    //completed list
   
    List<MasterTable> completedBooks;
    completedBooks = UserBooks.getCompleted(currUser.getUser_id());
    System.out.println("after first list created");


    JLabel notStarted = new JLabel("Plan to Read:");
    notStarted.setFont(titleFont);
    
    //planning list
    List<MasterTable> planningBooks;
	planningBooks = UserBooks.getPlanning(currUser.getUser_id());

    JButton btnLogout = new JButton("Log Out");
    btnLogout.setFont(mainFont);
    btnLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin.logout(currUser.getUser_name());
                dispose();
            }

        });
    
    JButton btnUpdate = new JButton("Update Book");
    btnUpdate.setFont(mainFont);
    btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateForm updateForm = new updateForm();
                updateForm.initialize(currUser);
                dispose();
            }

        });
    

    JButton btnAdd = new JButton("Add Book");
    btnAdd.setFont(mainFont);
    btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                addForm addForm = new addForm();
                addForm.initialize(currUser);
                dispose();
            }

        });

    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new GridLayout(0,1,5,5));

    JPanel BookPanel = new JPanel();
    BookPanel.setLayout(new GridLayout(0,1,5,5));

    JPanel BtnPanel = new JPanel();
    BookPanel.setLayout(new GridLayout(0,1,5,5));


    titlePanel.add(title);


    BookPanel.add(inProgress);
    
    JLabel booka = new JLabel(bookString.toHtmlStringAttributesStarted());
    booka.setFont(attributeFont);
    BookPanel.add(booka); 
    
    
    for (MasterTable i : inProgressBooks )
     {
        JLabel bookp = new JLabel(i.toHtmlStringDataStarted());
        bookp.setFont(dataFont);
        BookPanel.add(bookp);
    }
    
    BookPanel.add(notStarted);
    booka = new JLabel(bookString.toHtmlStringAttributesNotStarted());
    booka.setFont(attributeFont);
    BookPanel.add(booka);     
        
    for (MasterTable i : planningBooks )
    {
       JLabel bookp = new JLabel(i.toHtmlStringDataNotStarted());
       bookp.setFont(dataFont);
       BookPanel.add(bookp);
   }
    
    BookPanel.add(completed);
    booka = new JLabel(bookString.toHtmlStringAttributesCompleted());
    booka.setFont(attributeFont);
    BookPanel.add(booka);     
      
    
    for (MasterTable i : completedBooks )
    {
       JLabel bookp = new JLabel(i.toHtmlStringDataCompleted());
       bookp.setFont(dataFont);
       BookPanel.add(bookp);
   }


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
