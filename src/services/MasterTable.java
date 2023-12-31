package services;

import dao.books.Books;
import dao.users.Users;
import dao.users_books.Users_Books;

public class MasterTable {
	//Creates a master table that stores all useful data to pass to Jforms.  
	//Has toString methods to displays in html
	
	   private int user_id;
	   private String first_name;
	   private String last_name;
	   private String user_name;
	   private int book_id;
	   private String title;
	   private String author_first_name;
	   private String author_last_name;
	   private int num_of_pages;
	   private int pages_read;
	
	   //default constructor takes Books class, Users class, and UsersBooks class   
	public MasterTable(Books book, Users user, Users_Books userbook) {
		super();
		this.user_id = user.getUser_id();
		this.first_name = user.getFirst_name();
		this.last_name = user.getLast_name();
		this.user_name = user.getUser_name();
		this.book_id = book.getBook_id();
		this.title = book.getTitle();
		this.author_first_name = book.getAuthor_first_name();
		this.author_last_name = book.getAuthor_last_name();
		this.num_of_pages = book.getNum_of_pages();
		this.pages_read = userbook.getPages_read();
	}
	   //Getter for the user id
	public int getUser_id() {
		return user_id;
	}
	//Setter for the user id
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	//Getter for the first_name
	public String getFirst_name() {
		return first_name;
	}
	//Setter for the first_name
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	//Getter for the last_name
	public String getLast_name() {
		return last_name;
	}
	//Setter for the last_name
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	//Getter for the user_name
	public String getUser_name() {
		return user_name;
	}
	//Setter for the user_name
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	//Getter for the book id
	public int getBook_id() {
		return book_id;
	}
	 //Setter for the book id
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	//Getter for the book title
	public String getTitle() {
		return title;
	}
	  //Setter for the book title
	public void setTitle(String title) {
		this.title = title;
	}
	   //Getter for the authors first name
	public String getAuthor_first_name() {
		return author_first_name;
	}
	//Setter for the authors first name
	public void setAuthor_first_name(String author_first_name) {
		this.author_first_name = author_first_name;
	}
	//Getter for the authors last name
	public String getAuthor_last_name() {
		return author_last_name;
	}
	//Setter for the authors last name
	public void setAuthor_last_name(String author_last_name) {
		this.author_last_name = author_last_name;
	}
	  //Getter for number of pages in a book
	public int getNum_of_pages() {
		return num_of_pages;
	}
	   //Setter for number of pages in a book
	public void setNum_of_pages(int num_of_pages) {
		this.num_of_pages = num_of_pages;
	}
	//Getter for number of pages read 
	public int getPages_read() {
		return pages_read;
	}
	//Setter for number of pages read 
	public void setPages_read(int pages_read) {
		this.pages_read = pages_read;
	}
	



// Helper method to convert a string to Title Case
private String toTitleCase(String input) {
    StringBuilder titleCase = new StringBuilder();
    boolean nextTitleCase = true;

    for (char c : input.toCharArray()) {
        if (Character.isSpaceChar(c) || c == '-') {
            nextTitleCase = true;
        } else if (nextTitleCase) {
            c = Character.toTitleCase(c);
            nextTitleCase = false;
        } else {
            c = Character.toLowerCase(c);
        }
        titleCase.append(c);
    }

    return titleCase.toString();
}
//to string method to stringify the data
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("MasterTable:\n");
    sb.append(String.format("%-8s %-12s %-11s %-13s %-8s %-24s %-17s %-16s %-13s %-11s%n",
            toTitleCase("user_id"), toTitleCase("first_name"), toTitleCase("last_name"),
            toTitleCase("user_name"), toTitleCase("book_id"), toTitleCase("title"),
            toTitleCase("author_first_name"), toTitleCase("author_last_name"),
            toTitleCase("num_of_pages"), toTitleCase("pages_read")));
    sb.append(String.format("%-8d %-12s %-11s %-13s %-8d %-24s %-17s %-16s %-13d %-11d%n",
            user_id, first_name, last_name, user_name, book_id,
            title, author_first_name, author_last_name, num_of_pages, pages_read));
    return sb.toString();
}
//to string method to stringify the data in HTML for books that user has not started to read 
//Attributes Column
public String toHtmlStringDataStarted() {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><pre>");

    String authorFullName = toTitleCase(author_last_name + ", " + author_first_name);
    double percentage = (pages_read != 0) ? (double) pages_read / num_of_pages  * 100 : 0;
    sb.append(String.format("<font color='lightblue'>%-24s%-25s%-18d%-6.2f%%<br></font>",
            title, authorFullName, pages_read, percentage));
    sb.append("</pre></html>");
    return sb.toString();
}
//to string method to stringify the data in HTML for books that user has not started to read 
//Data from list Column
public String toHtmlStringDataNotStarted() {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><pre>");

    String authorFullName = toTitleCase(author_last_name + ", " + author_first_name);
    sb.append(String.format("<font color='lightblue'>%-24s%-25s%-18d</font><br>",
            title, authorFullName, num_of_pages));
    sb.append("</pre></html>");
    return sb.toString();
}
//to string method to stringify the data in HTML for books that user has completed
//Data from list Column

public String toHtmlStringDataCompleted() {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><pre>");

    String authorFullName = toTitleCase(author_last_name + ", " + author_first_name);
    sb.append(String.format("<font color='lightblue'>%-24s%-25s%-18d</font><br>",
            title, authorFullName, num_of_pages));
    sb.append("</pre></html>");
    return sb.toString();
}








	   
	   
}
