<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Sopa de letras</title>
  
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <script language="javascript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
    <script type="text/javascript" src="wordfind.js"></script> 
    <script type="text/javascript" src="wordfindgame.js"></script> 

</head>
<body>

    <div id='juego'></div>
    <div id='Palabras'></div>
    
    <div><button id='solve'>Resolver el juego</button></div>
    <script>
    var words = ['Develoteca','cursos','ayuda','Videos','Tutoriales','Plugins'];
    var gamePuzzle = wordfindgame.create(words, '#juego', '#Palabras'); 
        
    var puzzle = wordfind.newPuzzle(words,{height: 18, width:18, fillBlanks: false});
    wordfind.print(puzzle);   
        
    $('#solve').click( function() {wordfindgame.solve(gamePuzzle, words);});
        
    </script>
    
</body>
</html>