package co.edu.uco.unidaddeportivaelbernabeu.business.usecase;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.DeporteDomain;

import java.util.List;

public interface ConsultarDeportes {
    List<DeporteDomain> ejecutar(DeporteDomain deporte);
}
