package modelo.dto;

import java.util.Comparator;

public class OrdenadorPorNombreSeccion implements Comparator <Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		// TODO Auto-generated method stub
		return o1.getSeccion().getNombre().compareTo(o2.getSeccion().getNombre());
	}
	
	

}
