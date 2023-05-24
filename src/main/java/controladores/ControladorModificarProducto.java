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
import modelo.dao.ModeloSeccion;
import modelo.dto.Producto;

/**
 * Servlet implementation class ControladorModificarProducto
 */
@WebServlet("/ControladorModificarProducto")
public class ControladorModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorModificarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		
		request.setAttribute("secciones", modeloSeccion.getSecciones());
		
		Producto producto = new Producto();
		ModeloProducto modeloProducto = new ModeloProducto();
		
		producto = modeloProducto.getProducto(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("producto", producto);
		
		request.getRequestDispatcher("FormularioModificar.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloProducto modeloProducto = new ModeloProducto();
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		
		Producto producto = new Producto();
		
		producto.setId(Integer.parseInt(request.getParameter("id")));
		producto.setCodigo(request.getParameter("codigo"));
		producto.setNombre(request.getParameter("nombre"));
		producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		String a = request.getParameter("caducidad");
		Date caducidad = null;
		try {
			caducidad = new SimpleDateFormat("yyyy-MM-dd").parse(a);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		producto.setCaducidad(caducidad);
		producto.setSeccion(modeloSeccion.getSeccion(Integer.parseInt(request.getParameter("seccion"))));
		
		
		modeloProducto.modificarProducto(producto);
		
		response.sendRedirect("ControladorVerProductos");
				
				
	}

}
