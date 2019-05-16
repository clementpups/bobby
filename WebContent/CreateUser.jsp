<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import= "javax.servlet.http.HttpSession, fr.epsi.jeeProject.beans.Utilisateur";%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create User</title>
<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="row">
    <form class="col s12" method="post" action="CreateUserServlet">
      <div class="row">
        <div class="input-field col s6">
          <input id="name" name="name" type="text" class="validate">
          <label for="Pseudo">Pseudo</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="password" name="password" type="password" class="validate">
          <label for="password">Password</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="email" name="email" type="email" class="validate">
          <label for="email">Email</label>
        </div>
      </div>
      <%
       	Utilisateur user = (Utilisateur)session.getAttribute("User");
      	if(user.getAdmin() == true) { %>
	      <div class="row">
	      	<div class="input-field col s6">
	      		<label>
	        		<input type="checkbox" />
	        		<span>Est Admin</span>
	      		</label>
	      	</div>
	      </div> 
      <% } %>
      <div class="row">
      	<button class="btn waves-effect waves-light" type="submit" value="Create">Create</button>
      </div>
    </form>
    <!--JavaScript at end of body for optimized loading-->
  <script type="text/javascript" src="js/materialize.min.js"></script>
  </div>

</body>
</html>