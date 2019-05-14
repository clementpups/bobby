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
    <form class="col s12" method="post" action="CreatePostPage">
      <div class="row">
        <div class="input-field col s6">
          <input id="post" name="post" type="text" class="validate">
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
