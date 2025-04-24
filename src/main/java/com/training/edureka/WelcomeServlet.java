package com.training.edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	String action=request.getParameter("action");
    	
    	
    	/*ServletContext context=getServletContext();
    	ServletConfig config=getServletConfig();
    	String dbUrl=context.getInitParameter("dburl");
    	//String dbUrl=config.getInitParameter("url");
    	String dbUser=config.getInitParameter("dbuser");
    	String dbPwd=config.getInitParameter("dbpwd");
    	
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection created");*/
    	
    	
    	
    	if(action.equalsIgnoreCase("register")) {
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String email=request.getParameter("email");
    	String phone=request.getParameter("phone");
    	out.println("<h1>Welcome to our world! Dear "+username+" </h1>");
    	
    	ServletContext context=getServletContext();
    	ServletConfig config=getServletConfig();
    	String dbUrl=context.getInitParameter("dburl");
    	//String dbUrl=config.getInitParameter("url");
    	String dbUser=config.getInitParameter("dbuser");
    	String dbPwd=config.getInitParameter("dbpwd");
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			System.out.println("Connection created");
			PreparedStatement ps=con.prepareStatement("insert into appuser values(?,?,?,?)");
			ps.setString(1,username);
			ps.setString(2,password);
			ps.setString(3, email);
			ps.setString(4, phone);
			int count=ps.executeUpdate();
			if(count>=1) {
				out.println("<h1>Welcome"+username+"</h1>");
			}
			else {
				out.println("<h1>Try agin after 5 mins");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	}
    	else if(action.equalsIgnoreCase("update")) {
    		String ousername=request.getParameter("ousername");
    		String username=request.getParameter("nusername");
        	String password=request.getParameter("npassword");
        	String email=request.getParameter("nemail");
        	String phone=request.getParameter("nphone");
        	out.println("<h1>Welcome to our world! Dear "+username+" </h1>");
        	
        	ServletContext context=getServletContext();
        	ServletConfig config=getServletConfig();
        	String dbUrl=context.getInitParameter("dburl");
        	//String dbUrl=config.getInitParameter("url");
        	String dbUser=config.getInitParameter("dbuser");
        	String dbPwd=config.getInitParameter("dbpwd");
        	
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
    			Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
    			System.out.println("Connection created");
    			PreparedStatement ps=con.prepareStatement("Update appuser set username=?,pwd=?,email=?,phone=? where username=?");
    			ps.setString(1,username);
    			ps.setString(2,password);
    			ps.setString(3, email);
    			ps.setString(4, phone);
    			ps.setString(5, ousername);
    			int count=ps.executeUpdate();
    			if(count>=1) {
    				out.println("<h1>Updated"+username+"</h1>");
    			}
    			else {
    				out.println("<h1>Try agin after 5 mins");
    			}
    			
    		} catch (SQLException | ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	}
    	else if(action.equalsIgnoreCase("view")){
    		ServletContext context=getServletContext();
        	ServletConfig config=getServletConfig();
        	String dbUrl=context.getInitParameter("dburl");
        	//String dbUrl=config.getInitParameter("url");
        	String dbUser=config.getInitParameter("dbuser");
        	String dbPwd=config.getInitParameter("dbpwd");
        	
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
    			Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
    			System.out.println("Connection created");
    			PreparedStatement ps=con.prepareStatement("Select * from appuser");
    			ResultSet rs=ps.executeQuery();
    			out.println("<table border="+1+">");
    			out.println("<th>");
    			out.println("<tr><td>Username</td>");
    			
    			while(rs.next()) {
    				out.println("<t");
    			}
    			out.println("</table>");
    			
    		} catch (SQLException | ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	}
    	
    	
    	
    	
    
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out=response.getWriter();
    	response.setContentType("text/html");
    	out.println("<h1>Hello</h1>");
    	String qs=request.getQueryString();
    	String kv[]=qs.split("&");
    	out.println("Hello"+qs +kv[0]+kv[1]);
    }
    
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		for(int i=1;i<=5;i++) {
			out.println("<h"+i+">Hello from Welcome Servlet "+i+"</h"+i+">");
		}
		out.println("<h2> Its "+new java.util.Date()+"@server...</h2>");
		LocalTime obj=LocalTime.now();
		out.println("<h2> Its "+obj+"@server...</h2>");
		SimpleDateFormat sd = new SimpleDateFormat(
	            "yyyy.MM.dd G 'at' HH:mm:ss z");
	        Date date = new Date();
	        // TODO: Avoid using the abbreviations when fetching time zones.
	        // Use the full Olson zone ID instead.
	        sd.setTimeZone(TimeZone.getTimeZone("IST"));
	        out.println(sd.format(date));
	        
	}*/

}
