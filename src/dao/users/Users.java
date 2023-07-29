package dao.users;

//POJO class for Users table
public class Users {
   private int user_id;
   private String first_name;
   private String last_name;
   private String user_name;
   private String user_pswd;
   private boolean authenticate = false;
   // Method to auttenticate login
   public boolean isAuthenticate() {
	   return authenticate;
   }
   // Method to set authenticate
   public void setAuthenticate(boolean authenticate) {
	   this.authenticate = authenticate;
   }

   // Cosntructor for users incldues every variables inside the class 
   public Users(int user_id, String first_name, String last_name, String user_name, String user_pswd) {
      this.user_id = user_id;
      this.first_name = first_name;
      this.last_name = last_name;
      this.user_name = user_name;
      this.user_pswd = user_pswd;
   }
   // Constructor for users excluding users Id since user ID is set to auto increment in sql
   public Users(String first_name, String last_name, String user_name, String user_pswd) {
      this.first_name = first_name;
      this.last_name = last_name;
      this.user_name = user_name;
      this.user_pswd = user_pswd;
   }
   // Getters and setters for user id
   public int getUser_id() {
      return this.user_id;
   }

   public void setUser_id(int user_id) {
      this.user_id = user_id;
   }

   // Getters and setters for first name   
   public String getFirst_name() {
      return this.first_name;
   }

   public void setFirst_name(String first_name) {
      this.first_name = first_name;
   }

   // Getters and setters for last name
   public String getLast_name() {
      return this.last_name;
   }

   public void setLast_name(String last_name) {
      this.last_name = last_name;
   }

   // Getters and setters for user name
   public String getUser_name() {
      return this.user_name;
   }

   public void setUser_name(String user_name) {
      this.user_name = user_name;
   }

   // Getters and setters for user password
   public String getUser_pswd() {
      return this.user_pswd;
   }

   public void setUser_pswd(String user_pswd) {
      this.user_pswd = user_pswd;
   }
   
   @Override
   public String toString() {
      return "\nuser_id=" + this.user_id + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", user_name=" + this.user_name + ", password=" + this.user_pswd;
   }
}
