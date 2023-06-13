package controladores;

import java.io.IOException;


import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ModeloProducto;
import modelo.dto.Producto;
import modelo.dto.OrdenadorPorPrecio;
import modelo.dto.OrdenadorPorNombreSeccion;
import modelo.dto.OrdenadorPorFechaCaducidad;

/**
 * Servlet implementation class ControladorVerProductos
 */
@WebServlet("/ControladorVerProductos")
public class ControladorVerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorVerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloProducto modeloProducto = new ModeloProducto();
		ArrayList<Producto> productos = modeloProducto.getProductos();
		
		String orden = request.getParameter("orden");
		
		if (orden != null) {
			if (orden.equals("asc")) {
				productos.sort(new OrdenadorPorPrecio());
			
			} else if (orden.equals("desc")) {
				productos.sort(new OrdenadorPorPrecio().reversed());
			} else if (orden.equals("fecha_asc")) {
				productos.sort(new OrdenadorPorFechaCaducidad());
			} else if (orden.equals("fecha_desc")) {
				productos.sort(new OrdenadorPorFechaCaducidad().reversed());
			}
		}
		
		request.setAttribute("productos" , productos);
		
		request.getRequestDispatcher("VistaVerProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botonClickado = request.getParameter("boton");
		String botonOrdenarPorNombreSeccion = request.getParameter("ordenar");
		
		ModeloProducto modeloProducto = new ModeloProducto();
		ArrayList<Producto> productos = modeloProducto.getProductos(); //todos los productos
		ArrayList<Producto> productosFiltrados = new ArrayList<>(); //productos filtrados 
		
		if (botonClickado != null) {
			
			if(botonClickado.equals("buscar")) {
				String buscado = request.getParameter("buscador");
				
				for (Producto producto : productos) {
					if (producto.getNombre().contains(buscado) || producto.getCodigo().contains(buscado)) {
						productosFiltrados.add(producto);
					}
				}
			}
			
			
			if (botonClickado.equals("filtrar_precio")) {
				Double precioMinBuscado = Double.parseDouble(request.getParameter("precio_min"));
				Double precioMaxBuscado = Double.parseDouble(request.getParameter("precio_max"));
				
				for (Producto producto : productos) {
					if (producto.getPrecio() >= precioMinBuscado && producto.getPrecio() <= precioMaxBuscado) {
						productosFiltrados.add(producto);
					}
				}
			}
			
			
			if (botonClickado.equals("buscar_caracter")) {
				String[] caracteres = request.getParameter("buscador_eliminar").split(",");
				
				for (Producto producto : productos) {
					if (caracteres[0].charAt(0) == producto.getNombre().charAt(0) && caracteres[1].charAt(0) == producto.getNombre().charAt(producto.getNombre().length() - 1)) {
						System.out.println("a");
						productosFiltrados.add(producto);
					}
				}
			}
			
			request.setAttribute("productos", productosFiltrados);
		}
		
				
		if (botonOrdenarPorNombreSeccion != null) {
			
			if (botonOrdenarPorNombreSeccion.equals("seccion_nombre_ascendente") ) {
				productos.sort(new OrdenadorPorNombreSeccion());
			} else if (botonOrdenarPorNombreSeccion.equals("seccion_nombre_descendente")) {
				productos.sort(new OrdenadorPorNombreSeccion().reversed());
			}
			
			request.setAttribute("productos", productos);
			
		}
		
		request.getRequestDispatcher("VistaVerProductos.jsp").forward(request, response);
	}
	
}
