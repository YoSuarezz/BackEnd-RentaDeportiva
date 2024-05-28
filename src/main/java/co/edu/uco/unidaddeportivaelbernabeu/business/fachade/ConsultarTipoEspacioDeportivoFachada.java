package co.edu.uco.unidaddeportivaelbernabeu.business.fachade;

import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;

import java.util.List;

public interface ConsultarTipoEspacioDeportivoFachada {
    List<TipoEspacioDeportivoDTO> execute (TipoEspacioDeportivoDTO TipoEspacioDeportivo);
}
