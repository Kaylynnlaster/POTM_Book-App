package dao.books;

public class Books {
   //creating private variables
   private int book_id;
   private String title;
   private String author_first_name;
   private String author_last_name;
   private int num_of_pages;

   //Constructor
   public Books(int book_id, String title, String author_first_name, String author_last_name, int num_of_pages) {
      this.book_id = book_id;
      this.title = title;
      this.author_first_name = author_first_name;
      this.author_last_name = author_last_name;
      this.num_of_pages = num_of_pages;
   }
   public Books() {
	   }
   
   //Getter for the book id
   public int getBook_id() {
      return this.book_id;
   }
   //Setter for the book id
   public void setBook_id(int book_id) {
      this.book_id = book_id;
   }

   //Getter for the book title
   public String getTitle() {
      return this.title;
   }
   //Setter for the book title
   public void setTitle(String title) {
      this.title = title;
   }

   //Getter for the authors first name
   public String getAuthor_first_name() {
      return this.author_first_name;
   }

   //Setter for the authors first name
   public void setAuthor_first_name(String author_first_name) {
      this.author_first_name = author_first_name;
   }

   //Getter for the authors last name
   public String getAuthor_last_name() {
      return this.author_last_name;
   }
   //Setter for the authors last name
   public void setAuthor_last_name(String author_last_name) {
      this.author_last_name = author_last_name;
   }
   //Getter for number of pages in a book
   public int getNum_of_pages() {
      return this.num_of_pages;
   }
   //Setter for number of pages in a book
   public void setNum_of_pages(int num_of_pages) {
      this.num_of_pages = num_of_pages;
   }

   //to string method to stringify the data
   public String toString() {
      return "\nbook_id=" + this.book_id + ", title=" + this.title + ", author_first_name=" + this.author_first_name + ", author_last_name=" + this.author_last_name + ", num_of_pages=" + this.num_of_pages;
   }
// Helper method to convert a string to Title Case
private String toTitleCase(String input) {
   //Create a string builder 
    StringBuilder titleCase = new StringBuilder();
    //Created a boolean
    boolean nextTitleCase = true;

    //for loop for every character input it checks the input and converts it. 
    //Recursive and returns a string
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
//Turns the data into a string to better display the unser information on the GUI
   public String toHtmlStringAttributesStarted() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("<html><pre>");
	    sb.append(String.format("<font color='blue'>%-24s%-25s%-18s%-10s</b></font><br>",
	            toTitleCase("Title"), toTitleCase("Author_Name"),
	            toTitleCase("Pages Read"),
	            toTitleCase("Percentage")));
	    sb.append("</pre></html>");
	    return sb.toString();
	}
//Turns the data into a string to better display the unser information on the GUI
   public String toHtmlStringAttributesNotStarted() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("<html><pre>");
	    sb.append(String.format("<font color='blue'>%-24s%-25s%-18s</b></font><br>",
	            toTitleCase("Title"), toTitleCase("Author_Name"),
	            toTitleCase("Number of Pages")));
	    sb.append("</pre></html>");
	    return sb.toString();
	}
//Turns the data into a string to better display the unser information on the GUI
   public String toHtmlStringAttributesCompleted() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("<html><pre>");
	    sb.append(String.format("<font color='blue'>%-24s%-25s%-18s</b></font><br>",
	            toTitleCase("Title"), toTitleCase("Author_Name"),
	            toTitleCase("Number of Pages")));
	    sb.append("</pre></html>");
	    return sb.toString();
	} 
//Turns the data into a string to better display the unser information on the GUI
   public String toHtmlStringDataNotStarted() {
      StringBuilder sb = new StringBuilder();
      sb.append("<html><pre>");
  
      String authorFullName = toTitleCase(author_last_name + ", " + author_first_name);
      sb.append(String.format("<font color='lightblue'>%-24s%-25s%-18d</font><br>",
              title, authorFullName, num_of_pages));
      sb.append("</pre></html>");
      return sb.toString();
  }

}
