package controladores;

import java.io.IOException;

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
		
		request.getRequestDispatcher("FormularioModificar.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Producto producto = new Producto();
		ModeloProducto modeloProducto = new ModeloProducto();
		
		producto = modeloProducto.get
	}

}
