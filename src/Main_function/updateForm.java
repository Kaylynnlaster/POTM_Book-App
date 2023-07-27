package Main_function;

import java.awt.*;

import javax.swing.*;

import dao.users.Users;

public class updateForm extends JFrame{
    
    final private Font mainFont = new Font("Times new roman", Font.BOLD, 18);
    JTextField bookName;
    JTextField page_read;
    
    public void initialize() {
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
