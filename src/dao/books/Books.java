package dao.books;

public class Books {
   private int bookId;
   private String title;
   private String authorFirstName;
   private String authorLastName;
   private int numOfPages;

   public Books(String title, String authorFirstName, String authorLastName, int numOfPages) {
      this.title = title;
      this.authorFirstName = authorFirstName;
      this.authorLastName = authorLastName;
      this.numOfPages = numOfPages;
   }

   public Books(int bookId, String title, String authorFirstName, String authorLastName, int numOfPages) {
      this.bookId = bookId;
      this.title = title;
      this.authorFirstName = authorFirstName;
      this.authorLastName = authorLastName;
      this.numOfPages = numOfPages;
   }

   public int getBookId() {
      return this.bookId;
   }

   public void setBookId(int bookId) {
      this.bookId = bookId;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthorFirstName() {
      return this.authorFirstName;
   }

   public void setAuthorFirstName(String authorFirstName) {
      this.authorFirstName = authorFirstName;
   }

   public String getAuthorLastName() {
      return this.authorLastName;
   }

   public void setAuthorLastName(String authorLastName) {
      this.authorLastName = authorLastName;
   }

   public int getNumOfPages() {
      return this.numOfPages;
   }

   public void setNumOfPages(int numOfPages) {
      this.numOfPages = numOfPages;
   }

   @Override
   public String toString() {
      return "Books [bookId=" + bookId + ", title=" + title + ", authorFirstName=" + authorFirstName
            + ", authorLastName=" + authorLastName + ", numOfPages=" + numOfPages + "]";
   }
}
