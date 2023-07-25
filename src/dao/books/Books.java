package dao.books;

public class Books {
   private int book_id;
   private String title;
   private String author_first_name;
   private String author_last_name;
   private int num_of_pages;

   public Books(int book_id, String title, String author_first_name, String author_last_name, int num_of_pages) {
      this.book_id = book_id;
      this.title = title;
      this.author_first_name = author_first_name;
      this.author_last_name = author_last_name;
      this.num_of_pages = num_of_pages;
   }

   public int getBook_id() {
      return this.book_id;
   }

   public void setBook_id(int book_id) {
      this.book_id = book_id;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor_first_name() {
      return this.author_first_name;
   }

   public void setAuthor_first_name(String author_first_name) {
      this.author_first_name = author_first_name;
   }

   public String getAuthor_last_name() {
      return this.author_last_name;
   }

   public void setAuthor_last_name(String author_last_name) {
      this.author_last_name = author_last_name;
   }

   public int getNum_of_pages() {
      return this.num_of_pages;
   }

   public void setNum_of_pages(int num_of_pages) {
      this.num_of_pages = num_of_pages;
   }

   public String toString() {
      return "\nbook_id=" + this.book_id + ", title=" + this.title + ", author_first_name=" + this.author_first_name + ", author_last_name=" + this.author_last_name + ", num_of_pages=" + this.num_of_pages;
   }
}
