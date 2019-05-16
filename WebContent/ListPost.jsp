<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.List,fr.epsi.jeeProject.beans.Blog"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List post</title>
</head>
<body>

	<% 
	  List<Blog> list = (List<Blog>)request.getAttribute("list"); 
	  System.out.println(list);
	  for(Blog var : list ) { %>
	  		<a  href="/SEAN/BlogServlet?post=<%= var.getId() %>">Numero:<%= var.getTitre() %> <%= var.getDescription() %> </a>
  	<% } %> 
  
	<div class="row">
    <form class="col s12" method="post" action="ListPost">
    
    </form>

</body>
</html>