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
		
		request.setAttribute("productos" , productos);
		
		request.getRequestDispatcher("VistaVerProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botonClickado = request.getParameter("boton");
		ModeloProducto modeloProducto = new ModeloProducto();
		ArrayList<Producto> productos = modeloProducto.getProductos();
		ArrayList<Producto> productos2 = new ArrayList<>();
		
		if(botonClickado.equals("buscar")) {
			
			String buscado = request.getParameter("buscador");
			
			for (Producto producto : productos) {
				
				if (producto.getNombre().contains(buscado) || producto.getCodigo().contains(buscado)) {
					
					productos2.add(producto);
									
				}
				
			}
			
		}
		
		
		if (botonClickado.equals("filtrar_precio")) {
			
			Double precioMinBuscado = Double.parseDouble(request.getParameter("precio_min"));
			Double precioMaxBuscado = Double.parseDouble(request.getParameter("precio_max"));
			
			for (Producto producto : productos) {
				
				if (producto.getPrecio() >= precioMinBuscado && producto.getPrecio() <= precioMaxBuscado) {
					
					productos2.add(producto);
					
				}
				
			}
			
			
		}
		
		
		request.setAttribute("productos", productos2);
	
		request.getRequestDispatcher("VistaVerProductos.jsp").forward(request, response);
	}
	

}
