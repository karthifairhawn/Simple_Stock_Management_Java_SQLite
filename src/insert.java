import java.sql.*;
class insert
{
  public static void main(String a[])
  {
    Connection c=null;
    Statement s=null;	
    try
    {
       	Class.forName("org.sqlite.JDBC");
       	c=DriverManager.getConnection("jdbc:sqlite:t2.db");  	        
	
	      String st1="insert into stock values ('Pulses','10','1','30')";
        String st2="insert into stock values ('Rice','25','2','45')";
        String st3="insert into stock values ('Biscuit','40','3','5')";


        s=c.createStatement();
       	s.executeUpdate(st1);
        s.executeUpdate(st2);
        s.executeUpdate(st3);

        System.out.println("Inserted");        
 	      System.out.println("Saved");

        c.commit();
        s.close();
        c.close();

    
    }
    catch(Exception e){
      e.getStackTrace();
    }
}
}
