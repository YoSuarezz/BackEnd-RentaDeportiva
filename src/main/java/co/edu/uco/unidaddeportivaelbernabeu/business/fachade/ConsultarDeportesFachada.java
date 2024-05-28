package co.edu.uco.unidaddeportivaelbernabeu.business.fachade;

import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;

import java.util.List;

public interface ConsultarDeportesFachada {
    List<DeporteDTO> execute(DeporteDTO deporte);
}
