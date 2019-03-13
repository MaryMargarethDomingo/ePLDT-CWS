<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ page import = "java.text.SimpleDateFormat,
		java.util.Date,
		java.sql.Connection,
		java.sql.DriverManager,
		java.sql.ResultSet,
		java.sql.SQLException,
		java.sql.Statement"
	%>
	
	<h1 align = "center">Project <%= request.getAttribute("tableName") %></h1>
	
	<hr width = "80%">

	<form action="costworksheet.html" method = "post">
	
		<table width = "60%" border = "1" align = "center">
			<tr>
				<td>Plan Name</td>
				<td><select name = "planName">
					<%	
						try{
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cws_db","root","");
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery("SELECT * FROM `products` ORDER BY plan_name");
			
							while(rs.next()){
					%>
						<option value =<%=rs.getInt("ID")%>><%=rs.getString("plan_name") %></option>
					
					<%
							}
			
						}catch(SQLException sqle){
							System.out.println("SQLException in costworksheet.jsp");
							sqle.printStackTrace();
			
						}
	
					%>
					
				</select></td>
			</tr>
			
			<tr>
				<td>Quantity</td>
				<td><input type = "number" name = "qty" min = "1"></td>
			</tr>
			
			<tr>
				<td>Client's Payment Options</td>
				<td><select name = "paymentOptions">
					<option value = "OPEX Annual">OPEX - Annual</option>
					<option value = "OPEX Semi-annual">OPEX - Semi-annual</option>
					<option value = "OPEX QRC">OPEX - QRC</option>
					<option value = "OPEX MRC">OPEX - MRC</option>
					<option value = "OPEX OTC">OPEX - OTC</option>
					<option value = "CAPEX Annual">CAPEX - Annual</option>
					<option value = "CAPEX Semi-annual">CAPEX - Semi-annual</option>
					<option value = "CAPEX QRC">CAPEX - QRC</option>
					<option value = "CAPEX MRC">CAPEX - MRC</option>
				</select></td>
			</tr>
			
			<tr>
				<td>Contract Period In Months</td>
				<td><input type = "number" name = "contractPeriod" min = "1"></td>
			</tr>
			
			<input type = "hidden" name = "tableName" value = <%= request.getAttribute("tableName") %>>
			
			<tr>
				<td colspan = "2" align = "center"><input type = "submit" value = "Save"> &nbsp; <input type = "reset" value = "Clear"></td>
			</tr>
				
		</table>
	
	</form>
	
	<hr width = 80%>
	
	<table width = "150%" border = "1">
		<tr>
			<th>Plan Name</th>
			<th>Product Description</th>
			<th>Product Category</th>
			<th>Vendor / Service</th>
			<th>Quantity</th>
			<th> Unit Buying / Monthly / Quarterly / Semi-Annual / Annual Costs</th>
			<th>Total Buying Price</th>
			<th>Client's Payment Options</th>
			<th>Contract Period In</th>
			<th>No.	Of Period Amortized</th>
			<th>Cost Of Money</th>
			<th>Amortized Value</th>		
			<th>Applied Margin</th>
			<th>Unit Selling Price</th>
			<th>Total Selling Price</th>
		</tr>
	</table>

</body>
</html>