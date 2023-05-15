<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
<title>SuperMercado > Productos</title>
</head>
<body>
	
		
		<table class="table table-striped">
		
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Codigo</th>
			      <th scope="col">Nombre</th>
			      <th scope="col">Cantidad</th>
			      <th scope="col">Precio</th>
			      <th scope="col">Canducidad</th>
			    </tr>
			  </thead>
			  
			<c:forEach items="${productos}" var="producto">
			
			  <tbody>
			    <tr>
			      <th scope="row">${producto.id}</th>
			      <td>${producto.codigo}</td>
			      <td>${producto.nombre}</td>
			      <td>${producto.cantidad}</td>
			      <td>${producto.precio}</td>
			      <td>${producto.caducidad}</td>
			    </tr>
			  </tbody>
			  
			 </c:forEach>
		</table>
	
			
</body>
</html>