package dao.users;

public class Users {
   private int userId;
   private String firstName;
   private String lastName;
   private String userName;
   private String userPswd;
   private boolean authenticate = false;

   public boolean isAuthenticate() {
	   return authenticate;
   }
   public void setAuthenticate(boolean authenticate) {
	   this.authenticate = authenticate;
   }

   public Users(String firstName, String lastName, String userName, String userPswd) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this.userPswd = userPswd;
   }

   public Users(int userId, String firstName, String lastName, String userName, String userPswd) {
      this.userId = userId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this  .userPswd = userPswd;
   }

   public int getUserId() {
      return this.userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserPswd() {
      return this.userPswd;
   }

   public void setUserPswd(String userPswd) {
      this.userPswd = userPswd;
   }
   
   @Override
   public String toString() {
      return "Users [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
            + userName + ", userPswd=" + userPswd + ", authenticate=" + authenticate + "]";
   }

}
