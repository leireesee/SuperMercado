package controladores;

import java.io.IOException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ModeloProducto;
import modelo.dao.ModeloSupermercado;
import modelo.dao.ModeloProductoSupermercado;
import modelo.dao.ModeloSeccion;
import modelo.dto.Producto;

/**
 * Servlet implementation class ControladorInsertarProducto
 */
@WebServlet("/ControladorInsertarProducto")
public class ControladorInsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorInsertarProducto() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//tengo que enviar al jsp de insertar producto lo siguiente:
		//	- el arraylist de secciones para el select
		
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
		
		request.setAttribute("secciones", modeloSeccion.getSecciones());
		
		request.setAttribute("supermercados", modeloSupermercado.getSupermercados());
		
		request.getRequestDispatcher("FormularioInsertar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Producto producto = new Producto();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		ModeloProducto modeloProducto = new ModeloProducto();
		ModeloProductoSupermercado modeloProductoSupermercado = new ModeloProductoSupermercado();
		
		producto.setCodigo(request.getParameter("codigo"));
		producto.setNombre(request.getParameter("nombre"));
		producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		try {
			producto.setCaducidad(fecha.parse(request.getParameter("caducidad")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		producto.setSeccion(modeloSeccion.getSeccion(Integer.parseInt(request.getParameter("seccion"))));
		
		if (modeloProducto.verificarCodigoProducto(producto.getCodigo())!=null) {
			
			request.setAttribute("mensaje", "El código ya existe!");
			doGet(request, response);
			
		} else if (producto.getPrecio() < 0 || producto.getCantidad() < 0){
			
			request.setAttribute("mensaje", "Cantidad o precio incorrectos!");
			doGet(request, response);
			
		} else if (producto.getCaducidad().before(new Date())) {
			
			request.setAttribute("mensaje", "Fecha incorrecta!");
			doGet(request, response);
			
		} else if (producto.getSeccion().getId() == 0) {
			
			request.setAttribute("mensaje", "Elige una seccion!");
			doGet(request, response);
			
		} else {
			
			modeloProducto.insertarProducto(producto);
			
			int id_producto = modeloProducto.ultimoIdProducto();
			int id_supermercado = Integer.parseInt(request.getParameter("id_supermercado"));
			
			modeloProductoSupermercado.insertarProductoSupermercado(id_producto, id_supermercado);
			
		}
				
		response.sendRedirect("ControladorVerProductos");
		
	}

}
