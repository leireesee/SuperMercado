package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.Seccion;

public class ModeloSeccion extends Conector{
	
	public Seccion getSeccion(int idSeccion) {
		
		String sentenciaGetSeccion = "SELECT * FROM secciones WHERE id = ?";
		Seccion seccion = new Seccion();
		
		try {
			PreparedStatement st = super.conexion.prepareStatement(sentenciaGetSeccion);
			
			st.setInt(1, idSeccion);
			
			ResultSet rst = st.executeQuery();
			
//			while (rst.next()) {
//				seccion.setId(rst.getInt("id"));
//				seccion.setNombre(rst.getString("nombre"));
//			}
			
			//puedo hacerlo de las dos maneras pero es mas eficiente la segunda
			//puedo utilizar la segunda porque solo estoy buscando 1 seccion
			
			rst.next();
			seccion.setId(rst.getInt("id"));
			seccion.setNombre(rst.getString("nombre"));
			
			
			return seccion;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public ArrayList<Seccion> getSecciones(){
		
		String sentenciaGetSecciones = "SELECT * FROM secciones";
		ArrayList<Seccion> secciones = new ArrayList<>();
		
		try {
			PreparedStatement st = this.conexion.prepareStatement(sentenciaGetSecciones);
			
			ResultSet rst = st.executeQuery();
			
			while(rst.next()) {
				Seccion seccion = new Seccion();
				
				seccion.setId(rst.getInt("id"));
				seccion.setNombre(rst.getString("nombre"));
				
				secciones.add(seccion);
				
			}
			
			
			return secciones;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
 }
