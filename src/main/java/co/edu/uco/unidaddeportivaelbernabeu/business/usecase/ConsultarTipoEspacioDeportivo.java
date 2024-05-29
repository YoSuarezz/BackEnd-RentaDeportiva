package co.edu.uco.unidaddeportivaelbernabeu.business.usecase;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;

import java.util.List;

public interface ConsultarTipoEspacioDeportivo {
    List<TipoEspacioDeportivoDomain> ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo);
}
