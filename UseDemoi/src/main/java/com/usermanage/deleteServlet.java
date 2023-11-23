package com.usermanage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteUrl")
public class deleteServlet extends HttpServlet {
	private static final String query = "DELETE FROM studentDatase  where ID=?";
    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	//get printWriter
  	  PrintWriter pw = response.getWriter();
  	  //set content type
  	  response.setContentType("text/html");
  	  //get the id
  	  int id = Integer.parseInt(request.getParameter("id"));
  	  
  	  //link  bootstrap)
  	  pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
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
  		 int count = ps.executeUpdate();
  		pw.println("<div  class='card' style='margin:auto;width:500px;margin-top:100px;'>");
  		 if(count == 1) {
  			 pw.println("<h2 class='bg-success text-light text-center'>Record Deleted successfully</h2>");
  		 }else{
  			pw.println("<h2 class='bg-danger text-light text-center'>Record Not Deleted successfully</h2>");
  		 }

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
