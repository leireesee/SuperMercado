package modelo.dto;

import java.util.Comparator;

public class OrdenadorPorPrecio implements Comparator <Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		// TODO Auto-generated method stub
		if (o1.getPrecio() < o2.getPrecio()) {
			return -1;
		}
		return 1;
	}
	
}
