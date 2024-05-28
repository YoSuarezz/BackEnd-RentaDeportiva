package co.edu.uco.unidaddeportivaelbernabeu.business.fachade;

import co.edu.uco.unidaddeportivaelbernabeu.dto.UnidadDeportivaDTO;

import java.util.List;

public interface ConsultarUnidadesDeportivasFachada {
    List<UnidadDeportivaDTO> execute(UnidadDeportivaDTO unidadDeportiva);
}
