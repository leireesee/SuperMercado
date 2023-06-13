package modelo.dao;

import java.sql.Date;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.Producto;
import modelo.dao.ModeloSeccion;

public class ModeloProducto extends Conector{
	
	/*TODOS LOS PRODUCTOS*/
	public ArrayList<Producto> getProductos(){
		
		ArrayList<Producto> productos = new ArrayList<>();
		String sentenciaGetProductos = "SELECT * FROM productos";
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaGetProductos);
			
			ResultSet rst = st.executeQuery();
			
			while(rst.next()) {
				Producto producto = new Producto();
				
				producto.setId(rst.getInt("id"));
				producto.setCodigo(rst.getString("codigo"));
				producto.setNombre(rst.getString("nombre"));
				producto.setCantidad(rst.getInt("cantidad"));
				producto.setPrecio(rst.getDouble("precio"));
				producto.setCaducidad(rst.getDate("caducidad"));
				producto.setSeccion(modeloSeccion.getSeccion(rst.getInt("id_seccion")));
				
				productos.add(producto);
			}
			
			return productos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public void insertarProducto(Producto producto) {
		
		String sentenciaInsertarProducto = "INSERT INTO productos (codigo, nombre, cantidad, precio, caducidad, id_seccion) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaInsertarProducto);
			
			st.setString(1, producto.getCodigo());
			st.setString(2, producto.getNombre());
			st.setInt(3, producto.getCantidad());
			st.setDouble(4, producto.getPrecio());
			st.setDate(5, new Date(producto.getCaducidad().getTime()));
			st.setInt(6, producto.getSeccion().getId());
			
			
			st.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public String verificarCodigoProducto(String codigo) {
		
		String sentenciaVerificarCodigoProducto = "SELECT * FROM productos WHERE codigo = ?";
		
		Producto producto = new Producto();
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaVerificarCodigoProducto);
			
			st.setString(1, codigo);
			
			ResultSet rst = st.executeQuery();
			
			rst.next();
			
			
			return rst.getString("codigo");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void modificarProducto(Producto producto) {
		
		String sentenciaModificarProducto = "UPDATE productos SET codigo = ?, nombre = ?, cantidad = ?, precio = ?, caducidad = ?, id_seccion = ? WHERE id = ?";
		
		try {
			
			PreparedStatement st = this.conexion.prepareStatement(sentenciaModificarProducto);
			
			st.setString(1, producto.getCodigo());
			st.setString(2, producto.getNombre());
			st.setInt(3, producto.getCantidad());
			st.setDouble(4, producto.getPrecio());
			st.setDate(5, new Date(producto.getCaducidad().getTime()));
			st.setInt(6, producto.getSeccion().getId());
			
			st.setInt(7, producto.getId());
			
			st.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Producto getProducto(int id) {
		
		String sentenciaGetProducto = "SELECT * FROM productos WHERE id = ?";
		Producto producto = new Producto();
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		
		try {
			
			PreparedStatement st = this.conexion.prepareStatement(sentenciaGetProducto);
			
			st.setInt(1, id);
			
			ResultSet rst = st.executeQuery();
			
			rst.next();
			
			producto.setId(rst.getInt("id"));
			producto.setCodigo(rst.getString("codigo"));
			producto.setNombre(rst.getString("nombre"));
			producto.setCantidad(rst.getInt("cantidad"));
			producto.setPrecio(rst.getDouble("precio"));
			producto.setCaducidad(rst.getDate("caducidad"));
			producto.setSeccion(modeloSeccion.getSeccion(rst.getInt("id")));
			
			return producto;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public int ultimoIdProducto() {
		
		String sentenciaUltimoIdProducto = "SELECT max(id) FROM productos";
		int id_producto;
		
		try {
			
			PreparedStatement st = this.conexion.prepareStatement(sentenciaUltimoIdProducto);
			
			ResultSet rst = st.executeQuery();
			
			rst.next();
			
			id_producto = rst.getInt("max(id)");
			
			return id_producto;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	public void eliminarProducto(int id) {
		
		String sentenciaEliminarProducto = "DELETE FROM productos WHERE id = ?";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaEliminarProducto);
			
			st.setInt(1, id);
			
			st.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void eliminarProductoPorCodigo(String cod_producto) {
		
		String sentenciaEliminarProductoPorCodigo = "DELETE FROM productos WHERE codigo = ?";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaEliminarProductoPorCodigo);
			
			st.setString(1, cod_producto);
			
			st.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean verificarExistenciaPorCodigoProducto(String codigo) {
		
		String sentenciaVerificarExistenciaPorCodigoProducto = "SELECT * FROM productos WHERE codigo = ?";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaVerificarExistenciaPorCodigoProducto);
			
			st.setString(1, codigo);
									
			ResultSet rst = st.executeQuery();
						
			
			if (rst.next()) {
				
				return true;
				
			}					
			
		} catch (SQLException e) {
			
		}
				
		return false;
	}
	
}
