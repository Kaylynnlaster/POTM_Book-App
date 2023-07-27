package dao.users_books;

public class Users_Books {
	
	private int user_id;
	private int book_id;
	private int pages_read;

	public Users_Books(int user_id, int book_id, int pages_read) {
		this.user_id = user_id;
		this.book_id = book_id;
		this.pages_read = pages_read;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getPages_read() {
		return pages_read;
	}
	public void setPages_read(int pages_read) {
		this.pages_read = pages_read;
	}

}
