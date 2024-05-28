package co.edu.uco.unidaddeportivaelbernabeu.business.usecase;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.UnidadDeportivaDomain;

import java.util.List;

public interface ConsultarUnidadesDeportivas {
    List<UnidadDeportivaDomain> ejecutar(UnidadDeportivaDomain unidadDeportiva);
}

