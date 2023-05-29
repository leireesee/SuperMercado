package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.ProductoSupermercado;

public class ModeloProductoSupermercado extends Conector{
	
	public void insertarProductoSupermercado(int id_producto, int id_supermercado) {
		
		String sentenciainsertarProductoSupermercado = "INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?, ?)";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciainsertarProductoSupermercado);
			
			st.setInt(1, id_producto);
			st.setInt(2, id_supermercado);
			
			st.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ProductoSupermercado> buscarProductoEnSupermercados(int id_producto) {
		
		ArrayList<ProductoSupermercado> productosSupermercados = new ArrayList<>();
		String sentenciaComprobarSiEstaEnMasDeUnSupermercado = "SELECT * FROM productos_supermercados WHERE id_producto = ?";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaComprobarSiEstaEnMasDeUnSupermercado);
			
			st.setInt(1, id_producto);
			
			ResultSet rst = st.executeQuery();
			
			while(rst.next()) {
				
				ProductoSupermercado productoSupermercado = new ProductoSupermercado(); 
				
				productoSupermercado.setId(rst.getInt("id"));
				productoSupermercado.setId_producto(rst.getInt("id_producto"));
				productoSupermercado.setId_supermercado(rst.getInt("id_supermercado"));
				
				productosSupermercados.add(productoSupermercado);
				
			}
			
			return productosSupermercados;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public void eliminarProductoDeSupermercado(int id_producto) {
		
		String sentenciaEliminarProductoSupermercado = "DELETE FROM productos_supermercados WHERE id_producto = ?";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaEliminarProductoSupermercado);
			
			st.setInt(1, id_producto);
			
			st.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
