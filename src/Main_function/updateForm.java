package Main_function;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import dao.books.Books;
import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import services.UserBooks;
import services.UserLogin;

public class updateForm extends JFrame{
    
    //Creates the font
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    //Creates a text field for the book name and the pages read
    JTextField bookName;
    JTextField page_read;  
    //Creates a new bookdaoimpl object
    BooksDao books = new BooksDaoImpl();  
    //Creates a new book object  
    Books book;
    
    //Creates and sets the display up
    public void initialize(Users currUser) {
        //Creates a main label for the update form
        JLabel UpdateFormlb = new JLabel("Update Form", SwingConstants.CENTER);
        UpdateFormlb.setFont(mainFont);

        //Creates a label for the book text field
        JLabel bookTitle = new JLabel("Enter Book Title:");
        bookTitle.setFont(mainFont);

        //Sets the book text field
        bookName = new JTextField();
        bookName.setFont(mainFont);

        //Creates a label for the pages text field
        JLabel pgRead = new JLabel("Enter number of pages read (Ex: 89):");
        pgRead.setFont(mainFont);

        //Sets the pages text field
        page_read = new JTextField();
        page_read.setFont(mainFont);

        //Creates a panel to add all of the infromation to
        JPanel UpdatePanel = new JPanel();
        UpdatePanel.setLayout(new GridLayout(0,1,10,10));

        //Adds information to the panel
        UpdatePanel.add(UpdateFormlb);
        UpdatePanel.add(bookTitle);
        UpdatePanel.add(bookName);
        UpdatePanel.add(pgRead);
        UpdatePanel.add(page_read);

        //Creates a submit button that Gets the book name and the pages
        //Converts the page string to an int
        //Calls the method to find the book by the title
        //And calls the add pages read to update the users current information
        //for that book
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
                String pagesRead = page_read.getText();
                int numpgs = Integer.parseInt(pagesRead);
                book = books.findByTitle(title);
                boolean newProgress = UserBooks.addPagesRead(currUser.getUser_id(), book.getBook_id(), numpgs);

                if(!newProgress){
                    JOptionPane.showMessageDialog(updateForm.this,
                            "Failed adding your pages",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    updateForm updateForm = new updateForm();
                    updateForm.initialize(currUser);
                    dispose();
                }
                else {
                	mainFrame userList = new mainFrame();
                    userList.initialize(currUser);
                    dispose();
                }
            }

        });

        //Creates a cancel button to take user back to main board
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

        //Creates a Panel to add the buttons to
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        //Adds the buttons
        buttonsPanel.add(submitbtn);
        buttonsPanel.add(cancelbtn);

        //Postitions the panels
        add(UpdatePanel,BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        //Creates the display
        setTitle("Update Book!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
