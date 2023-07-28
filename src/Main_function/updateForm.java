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
    
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField bookName;
    JTextField page_read;  
    BooksDao books = new BooksDaoImpl();    
    Books book;
    
    
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
                System.out.println(currUser.getUser_id() + " " + book.getBook_id() +  " " + numpgs);
                int  newProgress = UserBooks.addPagesRead(currUser.getUser_id(), book.getBook_id(), numpgs);

                if(currUser != null){
                    //change curr user or create new user based of userBookInfo
                    mainFrame userList = new mainFrame();
                    userList.initialize(currUser);
                    dispose();
                }
//                else {
//                    JOptionPane.showMessageDialog(LoginForm.this,
//                            "Email or Password Invalid",
//                            "Try again",
//                            JOptionPane.ERROR_MESSAGE);
//                }
            
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
