package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
}
