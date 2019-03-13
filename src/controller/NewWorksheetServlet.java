package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import model.NewWorksheetBean;

@WebServlet("/newworksheetservlet.html")
public class NewWorksheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("errorpage.html");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		NewWorksheetBean nwb = new NewWorksheetBean();
		
		nwb.setCustomerName(request.getParameter("customerName"));
		nwb.setProjectDescription(request.getParameter("projectDescription"));
		nwb.setCustomerType(request.getParameter("customerType"));
		nwb.setOpportunityID(request.getParameter("opportunityID"));
		nwb.setCreatedBy(request.getParameter("createdBy"));
		System.out.println(nwb.getCreatedBy());
		
		nwb.setDate(format.format(date));
		
		saveNewWorksheetData(nwb.getCustomerName(), nwb.getProjectDescription(), 
				nwb.getCustomerType(), nwb.getOpportunityID(), nwb.getCreatedBy(), nwb.getDate());
		
		//createNewTableForWorksheet(nwb.getCustomerName(), nwb.getProjectDescription());
		
		request.setAttribute("tableName", nwb.getCustomerName() + " - " + nwb.getProjectDescription());
		request.getRequestDispatcher("costworksheet.jsp").forward(request, response);
		
	}
	
	private static void saveNewWorksheetData(String customerName, String projectDescription,
			String customerType, String opportunityID, String createdBy, String date){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cws_db","root","");
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO worksheets "
					+ "(customer_name, project_description, customer_type, opportunityID, created_by, date)"
					+ "VALUES (?,?,?,?,?,?)");
			
			pstmt.setString(1, customerName);
			pstmt.setString(2, projectDescription);
			pstmt.setString(3, customerType);
			pstmt.setString(4, opportunityID);
			pstmt.setString(5, createdBy);
			pstmt.setString(6, date);
			
			pstmt.execute();

		}catch(SQLException sqle){
			System.out.println("SQL Error in saveNewWorksheetData - NewWorksheetServlet.java");
			sqle.printStackTrace();
		
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	
	/*private static void createNewTableForWorksheet(String customerName, String projectDescription){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cws_db","root","");
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE " 
					+ customerName + projectDescription + 
					" (ID int AUTO_INCREMENT, "
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
					+ "Total_selling_price DOUBLE);");
			
			pstmt.executeUpdate();

		}catch(SQLException sqle){
			System.out.println("SQL Error in createNewTableForWorksheet - NewWorksheetServlet.java");
			sqle.printStackTrace();
		
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}*/

}
