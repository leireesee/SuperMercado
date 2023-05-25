package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.Supermercado;

public class ModeloSupermercado extends Conector{

	public ArrayList<Supermercado> getSupermercados(){
		
		ArrayList<Supermercado> supermercados = new ArrayList<>();
		
		String sentenciaGetSupermercados = "SELECT * FROM supermercados";
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaGetSupermercados);
			
			ResultSet rst = st.executeQuery();
			
			while(rst.next()) {
				Supermercado supermercado = new Supermercado();
				
				supermercado.setId(rst.getInt("id"));
				supermercado.setNombre(rst.getString("nombre"));
				
				supermercados.add(supermercado);
			}
			
			return supermercados;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}
}
