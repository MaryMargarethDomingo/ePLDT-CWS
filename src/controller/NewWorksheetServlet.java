package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.PreparedStatement;

import model.NewWorksheetBean;

@WebServlet("/worksheets.html")
public class NewWorksheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("errorpage.html");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("------------------------- NEW WORKSHEET SERVLET -------------------------");
		
		/*String userSession = "";
		
		Cookie[] cookies = request.getCookies();
		int i = 0;
		if(cookies != null){
			for (Cookie cookie : cookies ) {
				userSession = cookies[i].getName();
				System.out.println("INDEX COOKIE NAME: " + cookies[i].getName());
				System.out.println("INDEX COOKIE VALUE: " + cookies[i].getValue());
				i++;
			}
			
			if(!userSession.equals("userSession")){
				System.out.println("NO SESSION");
				response.sendRedirect("index.html");
			
			}else{
				response.sendRedirect("index.html");
			}
		
		}else{
			response.sendRedirect("index.html");
		}*/
		
		System.out.println("------------------------- NEW WORKSHEET SERVLET -------------------------");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		String worksheetTitle = request.getParameter("worksheetTitle");
		worksheetTitle = worksheetTitle.replace(" ", "_");
		
		NewWorksheetBean nwb = new NewWorksheetBean();
		
		nwb.setWorksheetTitle(worksheetTitle);
		nwb.setCustomerName(request.getParameter("customerName"));
		nwb.setProjectDescription(request.getParameter("projectDescription"));
		nwb.setCustomerType(request.getParameter("customerType"));
		nwb.setOpportunityID(request.getParameter("opportunityID"));
		nwb.setCreatedBy(request.getParameter("createdBy"));
		nwb.setType(request.getParameter("type"));
		System.out.println(nwb.getCreatedBy());
		
		nwb.setDate(format.format(date));
		
		System.out.println();
		
		saveNewWorksheetData(nwb.getWorksheetTitle(), nwb.getCustomerName(), nwb.getProjectDescription(), 
				nwb.getCustomerType(), nwb.getOpportunityID(), nwb.getCreatedBy(), nwb.getType(), nwb.getDate());
		
		if(createNewTableForWorksheet(nwb.getWorksheetTitle())){
			request.setAttribute("worksheetTitle", nwb.getWorksheetTitle());
			request.getRequestDispatcher("costworksheet.jsp").forward(request, response);
		}

	}
	
	private static void saveNewWorksheetData(String worksheetTitle, String customerName, String projectDescription,
			String customerType, String opportunityID, String createdBy, String type, String date){
		
		System.out.println(worksheetTitle);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cws_db","root","");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO worksheets "
					+ "(worksheet_title, customer_name, project_description, "
					+ "customer_type, opportunityID, created_by, type, date, status)"
					+ "VALUES (?,?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1, worksheetTitle);
			pstmt.setString(2, customerName);
			pstmt.setString(3, projectDescription);
			pstmt.setString(4, customerType);
			pstmt.setString(5, opportunityID);
			pstmt.setString(6, createdBy);
			pstmt.setString(7, type);
			pstmt.setString(8, date);
			pstmt.setString(9, "Okay");
			
			pstmt.execute();

		}catch(SQLException sqle){
			System.out.println("SQL Error in saveNewWorksheetData - NewWorksheetServlet.java");
			sqle.printStackTrace();
		
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	
	private static boolean createNewTableForWorksheet(String worksheetTitle){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cws_db","root","");
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE " 
					+ worksheetTitle 
					+ " (ID int AUTO_INCREMENT, "
					+ "PRIMARY KEY(ID), "
					+ "Plan_name VARCHAR(50), "
					+ "Product_category VARCHAR(50), "
					+ "Vendor VARCHAR(20), "
					+ "Qty INT, "
					+ "Unit_buying_costs DOUBLE, "
					+ "Total_buying_price DOUBLE, "
					+ "Clients_payment_options VARCHAR(50), "
					+ "Contract_period INT, "
					+ "Period_amortized DOUBLE, "
					+ "Cost_of_money DOUBLE, "
					+ "Amortized_value DOUBLE, "
					+ "Applied_margin DOUBLE, "
					+ "Unit_selling_price DOUBLE, "
					+ "Total_selling_price DOUBLE,"
					+ "Added_by VARCHAR(50), "
					+ "TCVRecurring DOUBLE);");
			
			pstmt.executeUpdate();
			
			conn.close();
			
			return true;

		}catch(SQLException sqle){
			System.out.println("SQL Error in createNewTableForWorksheet - NewWorksheetServlet.java");
			sqle.printStackTrace();
			
			return false;
		
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			
			return false;
		}
	}

}
