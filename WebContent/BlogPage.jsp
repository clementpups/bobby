<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,fr.epsi.jeeProject.beans.Blog,fr.epsi.jeeProject.beans.Reponse"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
</head>
<body>

<% String name = (String)request.getAttribute("postName");
   Blog blog = (Blog)request.getAttribute("Blog");
   List<Reponse> reponses= blog.getListOfReponses();
   String str = request.getParameter("description");%>
   
    <div class="row">
    <div class="col s12 m7">
      <div class="card">
   <span class="card-title"><%= name%> </span>
   <div class="card-content">
   <small><i>modifié le <%= blog.getDateModification() %></i></small>
   <p>Pour contacter l'auteur : <%= blog.getCreateur().getEmail()  %></p>
   <p><%= blog.getDescription() %></p>
   </div>
   </div>
   </div>
   </div>
   <% 
	  for(Reponse var : blog.getListOfReponses() ) { %>
	  	<div class="row">
    <div class="col s12 m7">
      <div class="card">
   <span class="card-title"><%= var.getBlogger().getEmail()%> </span>
   <div class="card-content">
	  		<%= var.getCommentaire() %>
	  	</div>
   </div>
   </div>
   </div>
   <% } %> 

	<div class="row">
    	<form class="col s12" method="post" action="BlogPage">
		</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
</body>

</html>