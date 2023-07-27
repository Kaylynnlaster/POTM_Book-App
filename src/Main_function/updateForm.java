package Main_function;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import Exceptions.BookIdNotFoundException;
import Exceptions.BookTitleNotFoundException;
import Exceptions.PageOverNumOfPagesException;
import Exceptions.UserIdBookIdNotFoundException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.books.Books;
import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import services.UserBooks;
import services.UserLogin;

public class updateForm extends JFrame{
    
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField bookName;
    JTextField page_read;
    
    public void initialize(Users currUser) {
        JLabel UpdateFormlb = new JLabel("Update Form", SwingConstants.CENTER);
        UpdateFormlb.setFont(mainFont);

        JLabel bookTitle = new JLabel("Enter Book Title:");
        bookTitle.setFont(mainFont);

        bookName = new JTextField();
        bookName.setFont(mainFont);

        JLabel pgRead = new JLabel("Enter number of pages read (Ex: 89):");
        pgRead.setFont(mainFont);

        page_read = new JTextField();
        page_read.setFont(mainFont);

        JPanel UpdatePanel = new JPanel();
        UpdatePanel.setLayout(new GridLayout(0,1,10,10));

        UpdatePanel.add(UpdateFormlb);
        UpdatePanel.add(bookTitle);
        UpdatePanel.add(bookName);
        UpdatePanel.add(pgRead);
        UpdatePanel.add(page_read);

        JButton submitbtn = new JButton("Update");
        submitbtn.setFont(mainFont);
        submitbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookName.getText();
                String pagesRead = page_read.getText();
                int numpgs = Integer.parseInt(pagesRead);
                BooksDaoImpl booksDao = new BooksDaoImpl();
                try {
                    List<Books> books = booksDao.findByTitle(title);
                    System.out.println(books);
                    Books book = books.get(0);
                    int id = book.getBookId();
                    boolean  newProgress = UserBooks.addPagesRead(currUser.getUserId(), id, numpgs);
                } catch (BookTitleNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (UserIdBookIdNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (BookIdNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (PageOverNumOfPagesException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }

        });

        JButton cancelbtn = new JButton("Cancel");
        cancelbtn.setFont(mainFont);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        buttonsPanel.add(submitbtn);
        buttonsPanel.add(cancelbtn);


        add(UpdatePanel,BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
        setTitle("Update Book!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
