package co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.ActualizarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.ConsultarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.CrearDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.TarifaEstandarEntity;

import java.util.List;

public interface TarifaEstandarDAO extends CrearDAO<TarifaEstandarEntity>, ActualizarDAO<TarifaEstandarEntity>, ConsultarDAO<TarifaEstandarEntity> {
    List<TarifaEstandarEntity> consultarPorNombre(String nombreTarifa);

    List<TarifaEstandarEntity> consultarPorTipoEspacioDeportivo(int tipoEspacioDeportivoId);

}
