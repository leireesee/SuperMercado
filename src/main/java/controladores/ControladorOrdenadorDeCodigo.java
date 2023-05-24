package controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ModeloProducto;
import modelo.dto.OrdenadorDeCodigo;
import modelo.dto.Producto;

/**
 * Servlet implementation class ControladorOrdenadorDeCodigo
 */
@WebServlet("/ControladorOrdenadorDeCodigo")
public class ControladorOrdenadorDeCodigo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorOrdenadorDeCodigo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrdenadorDeCodigo ordenador = new OrdenadorDeCodigo();
		
		ModeloProducto modeloProducto = new ModeloProducto();
		
		ArrayList<Producto> productos = new ArrayList<>();
		
		productos = modeloProducto.getProductos();
		
		String botonOrdenar = request.getParameter("ordenar");
		
		if(botonOrdenar.equals("ascendente")) {
			productos.sort(ordenador);
		}
		
		if (botonOrdenar.equals("descendente")) {
			productos.sort(ordenador.reversed());
		}
		
		request.setAttribute("productos", productos);
		
		request.getRequestDispatcher("VistaVerProductos.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
