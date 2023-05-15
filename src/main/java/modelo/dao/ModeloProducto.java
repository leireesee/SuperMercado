package modelo.dao;

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
				
				productos.add(producto);
			}
			
			return productos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
