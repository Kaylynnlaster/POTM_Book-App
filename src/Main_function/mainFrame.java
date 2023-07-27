package Main_function;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import Exceptions.BookIdNotFoundException;
import Exceptions.UserIdNotFoundException;

import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import dao.users.Users;
import services.MasterTable;
import services.UserBooks;
import services.UserLogin;

public class mainFrame extends JFrame{

    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    
    public void initialize(Users currUser) throws BookIdNotFoundException, UserIdNotFoundException {
    
    JLabel title = new JLabel("Your Books", SwingConstants.CENTER);
    title.setFont(mainFont);

    JLabel inProgress = new JLabel("In Progress:");
    inProgress.setFont(mainFont);

    
    // progress book list
    // System.out.println("before first list created");
    // List<MasterTable> inProgressBooks;
	// inProgressBooks = UserBooks.getProgress(currUser.getUserId());
    // System.out.println("after first list created");


    JLabel completed = new JLabel("Completed: ");
    completed.setFont(mainFont);

    //completed list
   
    List<MasterTable> completedBooks;
    // completedBooks = UserBooks.getCompleted(currUser.getUserId());
    completedBooks = new ArrayList<>();
    System.out.println("after first list created");


    JLabel notStarted = new JLabel("Plan to Read:");
    notStarted.setFont(mainFont);
    
    //planning list
    List<MasterTable> planningBooks;
	planningBooks = UserBooks.getPlanning(currUser.getUserId());

    JButton btnLogout = new JButton("Log Out");
    btnLogout.setFont(mainFont);
    btnLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin.logout(currUser.getUserName());
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
    
    // // Iterator iteratorProgress = inProgressBooks.iterator();
    // while(iteratorProgress.hasNext()) {
    //     JLabel bookp = new JLabel(iteratorProgress.next().toString());
    //     bookp.setFont(mainFont);
    //     BookPanel.add(bookp);
    // }
    

    BookPanel.add(notStarted);
    Iterator iteratorPlan = planningBooks.iterator();
    while(iteratorPlan.hasNext()) {
        JLabel bookns = new JLabel(iteratorPlan.next().toString());
        bookns.setFont(mainFont);
        BookPanel.add(bookns);
    }


    BookPanel.add(completed);

    Iterator iteratorCom = completedBooks.iterator();
    while(iteratorCom.hasNext()) {
        JLabel bookc = new JLabel(iteratorCom.next().toString());
        bookc.setFont(mainFont);
        BookPanel.add(bookc);
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
