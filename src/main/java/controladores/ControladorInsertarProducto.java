package controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ModeloProducto;
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
		
		request.setAttribute("secciones", modeloSeccion.getSecciones());
		
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
		
		modeloProducto.insertarProducto(producto);
		
		request.getRequestDispatcher("ControladorVerProductos").forward(request, response);
		
	}

}
