<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,fr.epsi.jeeProject.beans.Blog,fr.epsi.jeeProject.beans.Reponse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% String name = (String)request.getAttribute("postName");
   Blog blog = (Blog)request.getAttribute("Blog");
   List<Reponse> reponses= blog.getListOfReponses();
   String str = request.getParameter("description");%>
   <p>Voici le blog <%= name%> du <%= blog.getDateCreation() %> modifier <%= blog.getDateModification() %>.</p>
   <p>Pour contacter l'auteur : <%= blog.getCreateur().getEmail()  %></p>
   <p><%= blog.getDescription() %></p>
   
   <% 
	  for(Reponse var : blog.getListOfReponses() ) { %>
	  	<p>
	  		<%= var.getCommentaire() %>
	  	</p>
   <% } %> 

	<div class="row">
    	<form class="col s12" method="post" action="BlogPage">
		</form>
</body>
</html>