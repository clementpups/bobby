<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.List,fr.epsi.jeeProject.beans.Blog,fr.epsi.jeeProject.beans.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>List post</title>
	<!-- Ajout des icons -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    
	<!--Let browser know website is optimized for mobile-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
	<ul class="collection with-header">
        <li class="collection-header"><h4>Liste des postes</h4></li>
	   	<% 
	   	Utilisateur user = (Utilisateur)session.getAttribute("User");
		  List<Blog> list = (List<Blog>)request.getAttribute("list");
		  for(Blog var : list ) { %>
		  		<li class="collection-item"><div><%= var.getTitre() %> <%= var.getDescription() %>
		  		<a href="/BobForce/BlogServlet?ShowBlog=<%= var.getId() %>" class="secondary-content btn-floating waves-effect waves-light blue"><i class="material-icons">remove_red_eye</i></a>
  		<% if(user.getAdmin() == true || user.getEmail() == var.getCreateur().getEmail()){ %>
  			<form action="delete" method="post">
			    <input type="hidden" name="BlogId" value="<%=var.getId()%>" />
			    <input type="submit" class="btn waves-light btn red right" value="Delete"/>
			</form><% } %></div></li>
	  	<% } %> 
   
   
    </ul>
	<div class="row">
    <form class="col s12" method="post" action="ListPost">
    
    </form>
  <!--JavaScript at end of body for optimized loading-->
  <script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>