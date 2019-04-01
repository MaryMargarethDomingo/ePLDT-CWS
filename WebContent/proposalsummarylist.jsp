<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link rel = "stylesheet" href = "css/cws.css"/>
    <title>ePLDT CWS</title>
  </head>
  <body style="background-color:whitesmoke;">

    <%@ page import = "java.text.SimpleDateFormat,
    		java.util.Date,
    		java.sql.Connection,
    		java.sql.DriverManager,
    		java.sql.ResultSet,
    		java.sql.SQLException,
    		java.sql.Statement"
    	%>

    	<%
    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    		Date date = new Date();
    	%>
    	<br></br>
    	<h1 align = "center"> WELCOME BACK, <%= request.getAttribute("session") %></h1>

    <!--Navigation Bar-->
    <nav id = "nav">
      <ul>
        <li> <a href ="Home.html"> ePLDT CWS </a></li>
        <li> <a href ="CostWorksheet.html"> Cost Worksheet </a></li>
        <li> <a href ="#proposalSummary"> Proposal Summary </a></li>
        <li> <a href ="editproducts.html"> Product Catalog </a></li>
      </ul>
    </nav>
    <!--Button for CreateNewWorksheet-->
    <br></br>
        <a href="#popup1" class="button">Create New Worksheet</a>
    <!--Button for PopUp page-->
    <div id="popup1" class="overlay">
    	<div class="popup">
<form action="proposalsummary.jsp" method="POST">
    		<h2>New Project</h2>
    		<a class="close" href="#">&times;</a>
    	<p>
    		<label style="width:120px;">Worksheet Title:</label>
          <input style="width:150px;" type="text" name="worksheetTitle" required>
    		
    	</p>
        <p>
          <label style="width:120px;">Customer Name:</label>
          <input style="width:150px;" type="text" name="customerName" required>

          <label style="width:120px;">Customer Type:</label>
          <input style="width:150px;" type="text" name="customerType" required>
        </p>
        <p>
          <label style="width:120px;">Project Description:</label>
          <input style="width:150px;" type="text" name="projectDescription" required>

          <label style="width:120px;">Created By:</label>
          <input style="width:150px;" type="text" name="createdBy" value = "<%= request.getAttribute("session") %>" disabled>
        </p>
        <p>
          <label style="width:120px;">Opportunity ID:</label>
          <input style="width:150px;" type="text" name="opportunityID" required>

          <label style="width:120px;">Date:</label>
          <input style="width:150px;" type="text" name="date" disabled value=<%= format.format(date) %>>
        </p>
        <!--Save and Clear Button (Popup)-->
        <br></br>
        <div style="text-align: right;">
          <a href="#clear" class="clear">Clear</a>
          <a href="#save" class="save">Save</a>
        </div>
    	</div>
    </div>
    <hr>
    <div class="page" style="overflow-x:auto;">
      <table class="layout display responsive-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Worksheet Title</th>
            <th>Customer Name</th>
            <th>Project Description</th>
            <th>Customer Type</th>
            <th>Opportunity ID</th>
            <th>Created By</th>
            <th>Date Created</th>
            <th>Current Status</th>
            </tr>
    </thead>
    <tbody>

        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
		  <td></td>
          <td></td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>

        <%

    				try{
    					Class.forName("com.mysql.jdbc.Driver");
    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cws_db","root","");
    					Statement stmt = conn.createStatement();
    					ResultSet rs = stmt.executeQuery("SELECT * FROM `worksheets`");

    					while(rs.next()){
    %>

    			<tr>
    				<td><%= rs.getString("ID") %></td>
    				<td><%= rs.getString("worksheet_title") %></td>
					<td><%= rs.getString("customer_name") %></td>
					<td><%= rs.getString("project_description") %></td>
					<td><%= rs.getString("customer_type") %></td>
					<td><%= rs.getString("opportunityID") %></td>
					<td><%= rs.getString("created_by") %>
					<td><%= rs.getString("date") %></td>
					<td><%= rs.getString("status") %></td>
    			</tr>

    			<%
    					}

    				}catch(SQLException sqle){
    					System.out.println("SQLException in home.jsp");
    					sqle.printStackTrace();

    				}

    			%>

    </tbody>

</table>
</div>
</form>

  </body>
</html>
