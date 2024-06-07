package co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.data.dao.general.CrearDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.TarifaEstandarEntity;

import java.util.List;

public interface TarifaEstandarDAO extends CrearDAO<TarifaEstandarEntity> {
    List<TarifaEstandarEntity> consultarPorTipoEspacioDeportivo(int tipoEspacioDeportivoId);
}
