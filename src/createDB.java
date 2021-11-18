import java.sql.*;
class createDB
{
  public static void main(String a[])
  {
    Connection c=null;
    Statement s=null;	
    try
    {
      Class.forName("org.sqlite.JDBC");
      c=DriverManager.getConnection("jdbc:sqlite:t2.db");  
  
      s = c.createStatement();
      String sql = "CREATE TABLE stock (SNAME TEXT, SLEFT INT ,SID INT, SPRICE INT)"; 
      s.executeUpdate(sql);

      System.out.println("Databse Created");  
      s.close(); 
      c.close();
    }
    catch(Exception e)
    { 
      System.out.println(e);  
    }
}
}


