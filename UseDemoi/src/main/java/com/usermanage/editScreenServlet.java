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
@WebServlet("/editScreen")
public class editScreenServlet extends HttpServlet {
	private static final String query = "SELECT NAME,EMAIL,MOBILENUMBER,DATEOFBIRTH,GENDER,CITY FROM studentDatase where ID=?";
    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	//get printWriter
  	  PrintWriter pw = response.getWriter();
  	  //set content type
  	  response.setContentType("text/html");
  	  //get the id
  	  int id = Integer.parseInt(request.getParameter("id"));
  	  //link  bootstrap)
  	  pw.println("<link rel='stylesheet' href='css/bootstrap.css'> </linK>");
  	  //Load jdbc driver
  	  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
  	  //generate jdbc connection
  	  try(Connection con = DriverManager.getConnection("jdbc:mysql:///user","root","root")){
  		  PreparedStatement ps = con.prepareStatement(query);
  		  ps.setInt(1, id);
  		  ResultSet rs = ps.executeQuery();
  		  rs.next();

  		pw.println("<div class='card' style='width:500px;margin:auto;margin-top:100px'>");
  		pw.println("<form action='editUrl?id="+id+"' method='post' > " );
  		pw.println("<table class='table table-hover table-striped'>");
  		pw.println("<tr>");
  		pw.println("<td>NAME</td>");
  		pw.println("<td><input type='text' name='userName' value='"+rs.getString(1)+"' required></td>");
  		pw.println("</tr>");
  		
  		pw.println("<tr>");
  		pw.println("<td>EMAIL</td>");
  		pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+"' required></td>");
  		pw.println("</tr>");
  		
  		pw.println("<tr>");
  		pw.println("<td>MOBILE NUMBER</td>");
  		pw.println("<td><input type='text' name='mobileNumber' value='"+rs.getString(3)+"' required></td>");
  		pw.println("</tr>");
  		pw.println("<tr>");
  		pw.println("<td>DATE OF BIRTH</td>");
  		pw.println("<td><input type='date' name='Dob' value='"+rs.getString(4)+" ' required></td>");
  		pw.println("</tr>");
  		pw.println("<tr>");
  		pw.println("<td>CITY</td>");
  		pw.println("<td><input type='text' name='city' value='"+rs.getString(5)+"' required></td>");
  		pw.println("</tr>");
  		pw.println("<tr>");
  		pw.println("<td>GENDER</td>");
  		pw.println("<td><input type='radio' name='gender' value='"+rs.getString(6)+"' required>Male &nbsp;"
  				+ "<input type='radio' name='gender' value='"+rs.getString(6)+"' required>Female</td>");
  		pw.println("</tr>");
  		pw.println("<tr>");
  		pw.println("<td><input type='submit' value='save' class='btn btn-outline-success'></td>");
  		pw.println("<td><input type='reset' value='clear' class='btn btn-outline-danger'></td>");
  		pw.println("</tr>");
  		pw.println("</table>");
  		pw.println("</form>");

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
