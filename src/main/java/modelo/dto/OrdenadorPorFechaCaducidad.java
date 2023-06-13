package modelo.dto;

import java.util.Comparator;

public class OrdenadorPorFechaCaducidad implements Comparator <Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		// TODO Auto-generated method stub
		if (o1.getCaducidad().before(o2.getCaducidad())) {
			return -1;
		}
		return 1;
	}

}
