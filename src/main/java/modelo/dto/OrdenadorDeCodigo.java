package modelo.dto;

import java.util.Comparator;

public class OrdenadorDeCodigo implements Comparator<Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		// TODO Auto-generated method stub
		return o1.getCodigo().compareTo(o2.getCodigo());
	}
	
}
