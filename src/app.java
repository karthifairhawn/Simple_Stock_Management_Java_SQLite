import javax.swing.*;  
import java.awt.event.*;   
import java.sql.*;
import java.util.*;  
public class app extends JFrame implements ActionListener {  
 
    JLabel Lname, Lid, Lprice, Lleft;  
    static JTextField Tname, Tid, Tprice, Tleft;      
    JButton btn;  
    static JTable jt;
    JScrollPane sp;
    JFrame f;  
    static String data[][] = new String[10][4]; 
    String column[]={"ID","NAME","Stock Left","Price"};
    
    app() {      
        super("View and Insert Stock");  

        setLayout(null); 

        Lname = new JLabel("Enter Stock Name:");  
        Lname.setBounds(20, 20, 150, 20);  
        add(Lname);

        Tname = new JTextField(20);  
        Tname.setBounds(130, 20, 200, 20);  
        add(Tname);

        Lid = new JLabel("Enter Stock ID:");  
        Lid.setBounds(20, 40, 150, 20);  
        add(Lid);

        Tid = new JTextField(20);  
        Tid.setBounds(130, 40, 200, 20);  
        add(Tid);

        Lprice = new JLabel("Enter Stock Price:");  
        Lprice.setBounds(20, 60, 150, 20);  
        add(Lprice);

        Tprice = new JTextField(20);  
        Tprice.setBounds(130, 60, 200, 20);  
        add(Tprice);

        Lleft = new JLabel("Enter Stock Left:");  
        Lleft.setBounds(20, 80, 150, 20);  
        add(Lleft);

        Tleft = new JTextField(20);  
        Tleft.setBounds(130, 80, 200, 20);  
        add(Tleft);

     

        btn = new JButton("Submit");  
        btn.setBounds(130, 100, 100, 20);  
        btn.addActionListener(this);  
        add(btn);

        
                 
        jt=new JTable(data,column);    
        jt.setBounds(100,140,200,300);          
        sp=new JScrollPane(jt);    
        sp.setBounds(20,130,400,300); 
        add(sp);                  

           

         
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setSize(500, 500); 
         
    }

    public void actionPerformed(ActionEvent e) {  
        Connection c=null;
        Statement s=null;	
        int row=0;
        try {  
            Class.forName("org.sqlite.JDBC");
            c=DriverManager.getConnection("jdbc:sqlite:t2.db");  	        
            s=c.createStatement();
        
            String Iname = Tname.getText();
            int Iid = Integer.parseInt(Tid.getText());
            int Iprice = Integer.parseInt(Tprice.getText());
            int Ileft = Integer.parseInt(Tleft.getText());

            String query="insert into stock values ('"+Iname+"',"+Ileft+","+Iid+","+Iprice+")";            

            s.executeUpdate(query);

            System.out.println("Inserted");    
            
            Connection con = DriverManager.getConnection("jdbc:sqlite:t2.db"); 
            PreparedStatement st = con.prepareStatement("select * from stock");  
            ResultSet rs = st.executeQuery();  

            System.out.println(rs);            

            row=0;
            while(rs.next()) {  
                data[row][1] = (rs.getString(1));
                data[row][2] = (rs.getString(2));
                data[row][0] = (rs.getString(3));
                data[row++][3] = (rs.getString(4));
            }   
            jt=new JTable(data,column);    
            jt.setBounds(100,140,200,300);          
            sp=new JScrollPane(jt);    
            sp.setBounds(20,130,400,300); 
            add(sp);   
            
            Tid.setText(String.valueOf(++row));
            Tname.setText("");
            Tprice.setText("");
            Tleft.setText("");

            c.commit();
            s.close();
            c.close();
    
        }
        catch(Exception ee){
            ee.getStackTrace();
        }
              
    }  

    

    public static void main(String args[]) {  
        int row=0;
        try {  

            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:t2.db");  
            PreparedStatement st = con.prepareStatement("select * from stock");  
            ResultSet rs = st.executeQuery();  

            System.out.println(rs);            

            row=0;
            while(rs.next()) {  
                data[row][1] = (rs.getString(1));
                data[row][2] = (rs.getString(2));
                data[row][0] = (rs.getString(3));
                data[row++][3] = (rs.getString(4));
            }             
        } catch (Exception ex) {  
            System.out.println(ex);  
        } 
        new app();
        Tid.setText(String.valueOf(++row));  
        Tid.setEditable(false);
    }  
}
