package dao.usersBooks;

public class UsersBooks {
	
	private int userId;
	private int bookId;
	private int pagesRead;
	
	public UsersBooks(int userId, int bookId, int pagesRead) {
		this.userId = userId;
		this.bookId = bookId;
		this.pagesRead = pagesRead;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPagesRead() {
		return pagesRead;
	}

	public void setPagesRead(int pagesRead) {
		this.pagesRead = pagesRead;
	}

	@Override
	public String toString() {
		return "UsersBooks [userId=" + userId + ", bookId=" + bookId + ", pagesRead=" + pagesRead + "]";
	}

}
