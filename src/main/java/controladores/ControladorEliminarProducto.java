package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import modelo.dao.ModeloProducto;
import modelo.dto.Producto;
import modelo.dto.ProductoSupermercado;
import modelo.dao.ModeloProductoSupermercado;

/**
 * Servlet implementation class ControladorEliminarProducto
 */
@WebServlet("/ControladorEliminarProducto")
public class ControladorEliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorEliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloProducto modeloProducto = new ModeloProducto();
		Producto producto = new Producto();
		ModeloProductoSupermercado modeloProductoSupermercado = new ModeloProductoSupermercado();
		
		ArrayList<ProductoSupermercado> productosSuper = new ArrayList<>();
		productosSuper = modeloProductoSupermercado.buscarProductoEnSupermercados(Integer.parseInt(request.getParameter("id")));
		
		producto = modeloProducto.getProducto(Integer.parseInt(request.getParameter("id")));
		
		if (producto.getCantidad() > 0) {
			
			producto.setCantidad(producto.getCantidad() - 1);
			
			modeloProducto.modificarProducto(producto);
			
		} else if (producto.getCantidad() == 0 && productosSuper.size() > 0){
			
			modeloProductoSupermercado.eliminarProductoDeSupermercado(Integer.parseInt(request.getParameter("id")));
			
		} else if (producto.getCantidad() == 0 && productosSuper.size() == 0) {
			
			modeloProducto.eliminarProducto(Integer.parseInt(request.getParameter("id")));
			
		}
		
		
	
		
		response.sendRedirect("ControladorVerProductos");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String buscadoEliminar = request.getParameter("boton");
		String[] eliminarSeleccionados = request.getParameterValues("eliminar_seleccionados");
		
		
		if (buscadoEliminar != null) {//boton de eliminar por codigo
			String[] codigos = buscadoEliminar.split(",");
			
			ModeloProducto modeloProducto = new ModeloProducto();
			
			boolean todosCodigosExisten = true;
			
			for (String codigo : codigos) {
				
				if (modeloProducto.verificarExistenciaPorCodigoProducto(codigo) == false) {
					todosCodigosExisten = false;
				}
				
			}
			
			if (todosCodigosExisten == true) {
				
				for (String codigo : codigos) {
					modeloProducto.eliminarProductoPorCodigo(codigo);
				}
				
			}
		}
		
		if (eliminarSeleccionados != null) {//boton de eliminar por checkbox
			
			ModeloProducto modeloProducto = new ModeloProducto();
			
			for (String productoCheckeado : eliminarSeleccionados) {
				modeloProducto.eliminarProducto(Integer.parseInt(productoCheckeado));
			}
				
		}
		
		
		response.sendRedirect("ControladorVerProductos");
		
	}

}
