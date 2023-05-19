<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
	integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
	integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insertar Producto</title>
</head>
<body>

	<div style="width: 500px; box-shadow:0px 0px 15px lightblue; padding: 30px">

		<form action="ControladorInsertarProducto" method="post">
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Código</label>
				<input type="text" class="form-control" name="codigo">
			</div>
			
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Nombre</label>
				<input type="text" class="form-control" name="nombre">
			</div>
			
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Cantidad</label>
				<input type="number" class="form-control" name="cantidad">
			</div>
			
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Precio</label>
				<input type="number" class="form-control" name="precio">
			</div>
			
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Caducidad</label>
				<input type="date" class="form-control" name="caducidad">
			</div>
			
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Seccion</label>
				<select class="form-select" aria-label="Default select example" name="seccion">
					  					  
					  <c:forEach items="${secciones}" var="seccion">
					  	<option value="${seccion.id}">${seccion.nombre}</option>
					  </c:forEach>
					  
				</select>
			</div>
			
			<button type="submit" class="btn btn-primary">Insertar</button>
			
		</form>

	</div>
</body>
</html>