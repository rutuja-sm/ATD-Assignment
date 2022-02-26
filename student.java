package student.manage;
import java.io.*;
import java.sql.*;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.lang.NumberFormatException;
public class student {

	public static void main(String[] args) {
   try {
	   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   String user="root";
	   String password="rutu";
	   String url="jdbc:mysql://localhost:3306/Student";
	   Connection con=DriverManager.getConnection(url,user,password);
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   boolean isRunning=true;
	   
	   while(isRunning)
	   {
		   System.out.println("1.Insert");
		   System.out.println("2.Update");
		   System.out.println("3.Delete");
		   System.out.println("4.view all");
		   System.out.println("5.filter");

		   System.out.println("Enter your option");
		   int ch =Integer.parseInt(br.readLine());
	   
		   switch(ch)
		   {
		   case 1://Insert
			   System.out.println("Enter the Student_No,Student_Name,Student_DOB,Student_DOJ");
			 ps=con.prepareStatement ("INSERT INTO Student (STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) VALUES(?,?,?,?)");
			 ps.setInt(1,107);
	ps.setString(2,"Parth Shinde");  
	 java.sql.Date d = java.sql.Date.valueOf("2000-10-10");
	   ps.setDate(3, d);
	   java.sql.Date d1 = java.sql.Date.valueOf("2006-06-15");
	   ps.setDate(4, d1);
			   	   int ans=ps.executeUpdate();
			  System.out.println(ans+"row inserted");
			   break;
			  
		   case 2: //updated
			  // System.out.println("Enter the Student_No,Student_Name,Student_DOB,Student_DOJ");
			   ps=con.prepareStatement
			   ("UPDATE Student SET  STUDENT_NAME='Rutuja Matsagar' where STUDENT_NO=105");
			
		   int ans1=ps.executeUpdate();
		   if(ans1!=0)
			   System.out.println(ans1+"row updated");
		   else
			   System.out.println(ans1+"row not updated");
			   break;
			   
		   case 3://deleted
			   System.out.println("Enter the Student_No,Student_Name,Student_DOB,Student_DOJ");
			   ps=con.prepareStatement("DELETE FROM STUDENT WHERE STUDENT_NO=?");
				 ps.setInt(1,Integer.parseInt(br.readLine()));
			   int ans2=ps.executeUpdate();
			   System.out.println(ans2+"row deleted");
			   break;
			   
		   case 4:////Get a list of all students
			   ps=con.prepareStatement("select * from student ");
			   rs=ps.executeQuery();
			   System.out.println(" Student_No\tStudent_Name\tStudent_DOB\tStudent_DOJ");
			   while(rs.next())
	 System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDate(3)+"\t"+rs.getDate(4));
			   break;
		   case 5:
		        //Get one student information depending on the student id filter.
			   ps=con.prepareStatement("select * from student ");
	        rs = ps.executeQuery("select * from Student where STUDENT_NO=101");
	        while (rs.next())
	        {
	            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3) + " " + rs.getDate(4));
	            rs.close();
	        }
		   }//switch
   }
   }
	   catch(Exception e) {
		   System.out.println(e);
	   }
   }
	}
