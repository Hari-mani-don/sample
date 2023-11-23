package com.usermanage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/showData")
public class showUserServlet extends HttpServlet {
	private static final String query = "SELECT ID,NAME,EMAIL,MOBILENUMBER,DATEOFBIRTH,GENDER,CITY FROM studentDatase";
    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	//get printWriter
  	  PrintWriter pw = response.getWriter();
  	  //set content type
  	  response.setContentType("text/html");
  	  //link  bootstrap
  	  pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
  	pw.println("<marquee><h2 class='text-primary' style='margin-top: 10px;'>User Data</h2></marquee>");
  	  //Load jdbc driver
  	  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
  	  //generate jdbc connection
  	  try(Connection con = DriverManager.getConnection("jdbc:mysql:///user","root","root")){
  		  PreparedStatement ps = con.prepareStatement(query);
  		  ResultSet rs = ps.executeQuery();
  		  pw.println("<div   style='margin:100px 10px 0px 100px;width:500px;'>");
  		 pw.println("<table class='table table-hover table-striped'>");
  		 pw.println("<tr>");
  		 pw.println("<th>ID</th>");
  		 pw.println("<th>NAME</th>");
  		 pw.println("<th>EMAIL</th>");
  		 pw.println("<th>MOBILENUMBER</th>");
  		 pw.println("<th>DATEOFBIRTH</th>");
  		 pw.println("<th>CITY</th>");
  		 pw.println("<th>GENDER</th>");
  		 pw.println("<th>Edit</th>");
  		 pw.println("<th>Delete</th>");
  		 pw.println("</tr>");
  		 while(rs.next()) {
  			 pw.println("<tr>");
  	  		 pw.println("<td>"+rs.getInt(1)+"</td>");
  	  		 pw.println("<td>"+rs.getString(2)+"</td>");
  	  		 pw.println("<td>"+rs.getString(3)+"</td>");
  	  		 pw.println("<td>"+rs.getString(4)+"</td>");
  	  		 pw.println("<td>"+rs.getString(5)+"</td>");
  	  		 pw.println("<td>"+rs.getString(6)+"</td>");
  	  		 pw.println("<td>"+rs.getString(7)+"</td>");
  	  		 pw.println("<td><a href='editScreen?id="+rs.getString(1)+"'>Edit</a></td>");
  	  		 pw.println("<td><a href='deleteUrl?id=" +rs.getString(1)+"'>Delete</a></td>");
  	  		 pw.println("</tr>");
  		 }
  		 pw.println("</table>");
  	  } catch (SQLException e) {
			e.printStackTrace();
			pw.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h2>"+e.getMessage()+"</h2>");
		}
  	  pw.println("<a href='index.html' ><button  class='btn btn-outline-success'>Home</button></a>");
  	  pw.println("</div>");
  	  //close the stream 
  	  pw.close();
  			  
  }
    @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	doGet(request, response);
  }
}
