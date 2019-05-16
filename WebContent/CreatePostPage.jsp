<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Post Page</title>
<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
       
</head>

<body>
	<div class="row">
    <form class="col s12" method="post" action="CreatePostServlet">
      <div class="row">
          <div class="input-field col s6">
            <input id="titre" name="titre" type="text" data-length="10">
            <label for="input_text">Titre</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <textarea id="texte" name="texte" class="materialize-textarea" data-length="500"></textarea>
            <label for="textarea2">Texte</label>
          </div>
        </div>
             
      <div class="row">
      	<button class="btn waves-effect waves-light" type="submit" value="Post">Post</button>
      </div>
      
    </form>
    <!--JavaScript at end of body for optimized loading-->
  <script type="text/javascript" src="js/materialize.min.js"></script>
 </div>

</body>
