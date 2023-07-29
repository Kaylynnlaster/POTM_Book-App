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

    //Creates the different fonts
	final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
	final private Font titleFont = new Font("Times new roman", Font.BOLD, 20);
    final private Font attributeFont = new Font("Times new roman", Font.BOLD, 16);
    final private Font dataFont = new Font("Times new roman", Font.BOLD, 16);
   
    
    
    //Creates and organizes the GUI
    public void initialize(Users currUser) {
    
    //Creates new book object
    Books bookString = new Books();
    

    //Creates a new label for the display
    JLabel title = new JLabel(currUser.getFirst_name() + "'s Book Library", SwingConstants.CENTER);
    title.setFont(titleFont);
    title.setForeground(Color.BLUE);

    //Creates a new label for the in progress books
    JLabel inProgress = new JLabel("In Progress:");
    inProgress.setFont(titleFont);
    
    //progress book list
    List<MasterTable> inProgressBooks;
	inProgressBooks = UserBooks.getProgress(currUser.getUser_id());

    //Creates a new label for the completed book list
    JLabel completed = new JLabel("Completed: ");
    completed.setFont(titleFont);

    //completed list
    List<MasterTable> completedBooks;
    completedBooks = UserBooks.getCompleted(currUser.getUser_id());


    //Created label for the planed to read section
    JLabel notStarted = new JLabel("Plan to Read:");
    notStarted.setFont(titleFont);
    
    //planning list
    List<MasterTable> planningBooks;
	planningBooks = UserBooks.getPlanning(currUser.getUser_id());

    //Log out button created to log out the user and end the program
    JButton btnLogout = new JButton("Log Out");
    btnLogout.setFont(mainFont);
    btnLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin.logout(currUser.getUser_name());
                dispose();
            }

        });
    //update button created that takes the user to the update form
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
    
    //Add book button created that takes the user to the add book form
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
    
    
    //Panel created for the title
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new GridLayout(0,1,5,5));
    
    //Panel created for the Books and their information
    JPanel BookPanel = new JPanel();
    BookPanel.setLayout(new GridLayout(0,1,5,5));

    //Panel created for the buttons
    JPanel BtnPanel = new JPanel();
    BookPanel.setLayout(new GridLayout(0,1,5,5));


    //Title information added to the title panel
    titlePanel.add(title);

    //Progress label added to the Book Panel
    BookPanel.add(inProgress);
    
    //Label for the individual books converted to string and added
    JLabel booka = new JLabel(bookString.toHtmlStringAttributesStarted());
    booka.setFont(attributeFont);
    BookPanel.add(booka); 
    
    //for loop that loops through the progress list and adds the stringed data to the Book Panel
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
    
    //for loop that loops through the planning list and adds the stringed data to the Book Panel
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
      
    //for loop that loops through the completed list and adds the stringed data to the Book Panel
    for (MasterTable i : completedBooks )
    {
       JLabel bookp = new JLabel(i.toHtmlStringDataCompleted());
       bookp.setFont(dataFont);
       BookPanel.add(bookp);
   }


   //Buttons added to the button panel
    BtnPanel.add(btnUpdate);
    BtnPanel.add(btnAdd);
    BtnPanel.add(btnLogout);

   //Setting the position of the Panels
    add(titlePanel, BorderLayout.NORTH);
    add(BookPanel, BorderLayout.CENTER);
    add(BtnPanel, BorderLayout.SOUTH);

    //Creating the dashboard
    setTitle("Dashboard");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(650,650);
    setLocationRelativeTo(null);
    setVisible(true);
    }

}
