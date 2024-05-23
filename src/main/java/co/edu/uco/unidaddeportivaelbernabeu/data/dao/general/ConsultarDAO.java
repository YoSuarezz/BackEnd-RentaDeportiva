package co.edu.uco.unidaddeportivaelbernabeu.data.dao.general;

import java.util.List;

public interface ConsultarDAO<E> {
	List<E> consultar(E entidad);
}
