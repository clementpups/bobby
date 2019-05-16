<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.List,fr.epsi.jeeProject.beans.Blog"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List post</title>
</head>
<body>
	<ul class="collection with-header">
        <li class="collection-header"><h4>Liste des postes</h4></li>
        <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
	   	<% 
		  List<Blog> list = (List<Blog>)request.getAttribute("list");
		  for(Blog var : list ) { %>
		  		<li class="collection-item"><div><%= var.getTitre() %> <%= var.getDescription() %><a href="/jeePrject/BlogServlet?ShowBlog=<%= var.getId() %>" class="secondary-content"><i class="material-icons">send</i></a></div></li>
	  	<% } %> 
   
   
    </ul>
	<div class="row">
    <form class="col s12" method="post" action="ListPost">
    
    </form>

</body>
</html>