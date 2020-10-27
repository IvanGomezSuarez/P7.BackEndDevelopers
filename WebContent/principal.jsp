<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Principal</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<a style="color: white" class="navbar-toggler"><span
			class="navbar-toggler-icon"></span>Home</a>
		<div class="dropdown">
			<a style="color: white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> Cerrar Sesion</a>
			<div class="dropdown-menu text-center">
				<a><img src="img/user.png" height="70" width="70"></a><br> 
				<a>${usuario}</a> 
				<a>ejemplo@gmail.com</a> 
				<div class="dropdown-divider"></div>
				<a href="index.jsp" class="dropdown-item">Salir</a>
			</div>
		</div>
	</nav>
	<div class="container mt-4">
	<h1>Bienvenido a la aplicación de BackEndDevelopers.....<strong>Usuario: ${usuario}</strong></h1>
	</div>
	<div style="margin: auto; width: 50%; padding: 10px;">
	<a class="btn btn-primary" href="game.jsp" role="button">Jugar</a>
	</div>
      
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>