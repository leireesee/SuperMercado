package modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.Producto;

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
	
}
