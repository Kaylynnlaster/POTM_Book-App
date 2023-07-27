package services;

import dao.books.Books;
import dao.users.Users;
import dao.usersBooks.UsersBooks;

public class MasterTable {
	
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
	   
	public MasterTable(Books book, Users user, UsersBooks userbook) {
		super();
		this.user_id = user.getUserId();
		this.first_name = user.getFirstName();
		this.last_name = user.getLastName();
		this.user_name = user.getUserName();
		this.book_id = book.getBookId();
		this.title = book.getTitle();
		this.author_first_name = book.getAuthorFirstName();
		this.author_last_name = book.getAuthorLastName();
		this.num_of_pages = book.getNumOfPages();
		this.pages_read = userbook.getPagesRead();
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor_first_name() {
		return author_first_name;
	}
	public void setAuthor_first_name(String author_first_name) {
		this.author_first_name = author_first_name;
	}
	public String getAuthor_last_name() {
		return author_last_name;
	}
	public void setAuthor_last_name(String author_last_name) {
		this.author_last_name = author_last_name;
	}
	public int getNum_of_pages() {
		return num_of_pages;
	}
	public void setNum_of_pages(int num_of_pages) {
		this.num_of_pages = num_of_pages;
	}
	public int getPages_read() {
		return pages_read;
	}
	public void setPages_read(int pages_read) {
		this.pages_read = pages_read;
	}
	@Override
	public String toString() {
		return "MasterTable [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", book_id=" + book_id + ", title=" + title + ", author_first_name="
				+ author_first_name + ", author_last_name=" + author_last_name + ", num_of_pages=" + num_of_pages
				+ ", pages_read=" + pages_read + "]";
	}
	
	   
	   
}
