package Main_function;

import java.awt.*;

import javax.swing.*;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import dao.books.Books;
import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import services.GetAllBooks;
import services.UserBooks;

public class addForm extends JFrame{
	//Creates the different fonts
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
	final private Font titleFont = new Font("Times new roman", Font.BOLD, 20);
    final private Font attributeFont = new Font("Times new roman", Font.BOLD, 16);
    final private Font dataFont = new Font("Times new roman", Font.BOLD, 16);
    
    //Creates a text field for user input
    JTextField bookName;
    //Creates a new BooksDao object
    BooksDao books = new BooksDaoImpl();   
    //Creates a new book object 
    Books book; 
    
    //Creates the actual display
    public void initialize(Users currUser) {
    	
    	try {
			books.establishConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	book = new Books();
        //Creates a label for the form
        JLabel AddFormlb = new JLabel("What new book would you like to read", SwingConstants.CENTER);
        AddFormlb.setFont(titleFont);
        AddFormlb.setForeground(Color.BLUE);
        
        //Creates a label for the section to diplay all books
        JLabel allBooks = new JLabel(book.toHtmlStringAttributesAddForm());
        allBooks.setFont(attributeFont);

        //Creates a label for the text field
        JLabel bookTitle = new JLabel("Enter Book Title:");
        bookTitle.setFont(mainFont);

        //Creates the text field 
        bookName = new JTextField();
        bookName.setFont(mainFont);

        //Creates a panel to add the book adding information to
        JPanel AddPanel = new JPanel();
        AddPanel.setLayout(new GridLayout(0,1,10,10));

        //Adds the book information
        AddPanel.add(AddFormlb);
        AddPanel.add(bookTitle);
        AddPanel.add(bookName);

        //Creates a panel to add all of the book information to
        JPanel BookPanel = new JPanel();
        BookPanel.setLayout(new GridLayout(0,1,10,10));

        //for loop to iterate through a list of all books and display them to the
        //Book panel above. uses the Get all books service
        BookPanel.add(allBooks);
        List<Books> allBooksList = GetAllBooks.getAll();
        for(Books i : allBooksList){
            JLabel booka = new JLabel(i.toHtmlStringDataAddForm());
            booka.setFont(dataFont);
            BookPanel.add(booka);
        }

        //Create a submit button that establishes a connection and then updates
        //The users_book information for that specific user
        JButton submitbtn = new JButton("Update");
        submitbtn.setFont(mainFont);
        submitbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookName.getText();
                book = books.findByTitle(title);
                UserBooks.newPlanning(currUser.getUser_id(), book.getBook_id());
                mainFrame userList = new mainFrame();
                userList.initialize(currUser);
                dispose();
            
            }

        });

        //Cancel button created to pop back up the users information
        JButton cancelbtn = new JButton("Cancel");
        cancelbtn.setFont(mainFont);
        cancelbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame userList = new mainFrame();
                userList.initialize(currUser);
                dispose();
            
            }

        });

        //Creating a button for the panels and adding them
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        buttonsPanel.add(submitbtn);
        buttonsPanel.add(cancelbtn);

        //Adding everything to the display in proper order 
        add(AddPanel,BorderLayout.NORTH);
        add(BookPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        //Creating the actual display
        setTitle("Add Book!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 650);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
