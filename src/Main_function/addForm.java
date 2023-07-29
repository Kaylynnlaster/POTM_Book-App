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
import services.UserBooks;

public class addForm extends JFrame{
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField bookName;
    BooksDao books = new BooksDaoImpl();    
    Books book; 
    
    public void initialize(Users currUser) {
        JLabel AddFormlb = new JLabel("Add Form", SwingConstants.CENTER);
        AddFormlb.setFont(mainFont);

        JLabel allBooks = new JLabel("All Books:");
        allBooks.setFont(mainFont);

        BooksDaoImpl bookserv = new BooksDaoImpl();
        List<Books> allBooksList;
        allBooksList = bookserv.getAll();

        JLabel bookTitle = new JLabel("Enter Book Title:");
        bookTitle.setFont(mainFont);

        bookName = new JTextField();
        bookName.setFont(mainFont);

        JPanel AddPanel = new JPanel();
        AddPanel.setLayout(new GridLayout(0,1,10,10));

        AddPanel.add(AddFormlb);
        AddPanel.add(bookTitle);
        AddPanel.add(bookName);


        JPanel BookPanel = new JPanel();
        BookPanel.setLayout(new GridLayout(0,1,10,10));

        BookPanel.add(allBooks);
        for(Books i : allBooksList){
            JLabel booka = new JLabel(i.toHtmlStringDataNotStarted());
            booka.setFont(mainFont);
            BookPanel.add(booka);
        }

        JButton submitbtn = new JButton("Update");
        submitbtn.setFont(mainFont);
        submitbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					books.establishConnection();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                String title = bookName.getText();
                book = books.findByTitle(title);
                UserBooks.newPlanning(currUser.getUser_id(), book.getBook_id());
                mainFrame userList = new mainFrame();
                userList.initialize(currUser);
                dispose();
            
            }

        });

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

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        buttonsPanel.add(submitbtn);
        buttonsPanel.add(cancelbtn);


        add(AddPanel,BorderLayout.NORTH);
        add(BookPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        setTitle("Add Book!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 650);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
