package co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.ActualizarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.ConsultarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.CrearDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.EliminarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;

public interface TipoEspacioDeportivoDAO extends CrearDAO<TipoEspacioDeportivoEntity>, ActualizarDAO<TipoEspacioDeportivoEntity>,
        EliminarDAO, ConsultarDAO<TipoEspacioDeportivoEntity>{
}