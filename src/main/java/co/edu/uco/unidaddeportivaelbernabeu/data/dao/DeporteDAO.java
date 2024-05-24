package co.edu.uco.unidaddeportivaelbernabeu.data.dao;

import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.ActualizarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.ConsultarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.CrearDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.EliminarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;

public interface DeporteDAO extends CrearDAO<DeporteEntity>, ActualizarDAO<DeporteEntity>,
        EliminarDAO, ConsultarDAO<DeporteEntity> {

}
