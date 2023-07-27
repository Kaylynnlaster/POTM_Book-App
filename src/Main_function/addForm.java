package Main_function;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import Exceptions.BookIdNotFoundException;
import Exceptions.BookTitleNotFoundException;
import Exceptions.DuplicatedUserIdBookIdException;
import Exceptions.PageOverNumOfPagesException;
import Exceptions.UserIdBookIdNotFoundException;
import Exceptions.UserIdNotFoundException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.books.Books;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import services.UserBooks;

public class addForm extends JFrame{
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField bookName;
    
    public void initialize(Users currUser) {
        JLabel AddFormlb = new JLabel("Add Form", SwingConstants.CENTER);
        AddFormlb.setFont(mainFont);

        JLabel allBooks = new JLabel("All Books:");
        allBooks.setFont(mainFont);

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

        JButton submitbtn = new JButton("Update");
        submitbtn.setFont(mainFont);
        submitbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookName.getText();
                //call a findbookbytitle function and set it eqaual to a value
                //add book to the user_books database using the newPlanning function in UserBooks
                BooksDaoImpl booksDao = new BooksDaoImpl();
                try {
                    List<Books> books = booksDao.findByTitle(title);
                    System.out.println(books);
                    Books book = books.get(0);
                    int id = book.getBookId();
                    UserBooks.newPlanning(currUser.getUserId(), id);
                } catch (BookTitleNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (DuplicatedUserIdBookIdException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                mainFrame userList = new mainFrame();
                try {
                    userList.initialize(currUser);
                } catch (BookIdNotFoundException | UserIdNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dispose();
            
            }

        });

        JButton cancelbtn = new JButton("Cancel");
        cancelbtn.setFont(mainFont);
        cancelbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame userList = new mainFrame();
                try {
                    userList.initialize(currUser);
                } catch (BookIdNotFoundException | UserIdNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
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
