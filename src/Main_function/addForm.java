package Main_function;

import java.awt.*;

import javax.swing.*;

public class addForm extends JFrame{
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField bookName;
    
    public void initialize() {
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

        JButton cancelbtn = new JButton("Cancel");
        cancelbtn.setFont(mainFont);

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
