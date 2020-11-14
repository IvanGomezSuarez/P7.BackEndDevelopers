<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "service.WordService" %>

<!DOCTYPE html>
<html>
<head>
  <title>Sopa de letras</title>
  
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
    <script language="javascript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
    <script type="text/javascript" src="wordfind.js"></script> 
    <script type="text/javascript" src="wordfindgame.js"></script> 

</head>
<body>	
<nav class="navbar navbar-dark bg-dark">
		<a style="color: white" class="navbar-toggler" href="principal.jsp"><span
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
<div class="row my-1 mx-1">
  <div class="col-sm-6">
<div class="card text-center" style="width: 45rem;">
  <div class="card-header">
    Sopa de Letras
  </div>
  <div class="card-body">
    <p class="card-text">
    <div id='juego'></div>
    </p>
  </div>
</div>
</div>
 <div class="col-sm-6">
 <div style="padding:25px"></div>
    <div class="card" style="width: 20rem;">
      <div class="card-body">
        <h5 class="card-title">Debes encontrar...</h5>
        <p class="card-text">
        <div id='Palabras'></div>
        <div><button id='solve' class="btn btn-primary">Resolver el juego</button></div>
        </p>
      </div>
    </div> 
    </div>
    </div>
    
<!-- new Wordservice.devuelvePalabras();  -->
    
    <% System.out.print("Compruebo que java se ejecute"); %>
    <jsp:useBean id="link" scope="application" class = "service.WordService" /> 
    <script>
    //La respuesta del scriplet nos da ya los corchetes, igual no hace falta ponerlos en words = []
    //TO DO - Conseguir que muestre la información porque ya funciona
    var words =
    <% WordService t = new WordService(); 
    out.print(t.devuelvePalabras());
    System.out.println("ArrayList de t.devuelvePalabras() " + t.devuelvePalabras());
    %>
    ;
    var gamePuzzle = wordfindgame.create(words, '#juego', '#Palabras'); 
        
    var puzzle = wordfind.newPuzzle(words,{height: 18, width:18, fillBlanks: false});
    wordfind.print(puzzle);   
        
    $('#solve').click( function() {wordfindgame.solve(gamePuzzle, words);});
        
    </script>
    
</body>
</html>