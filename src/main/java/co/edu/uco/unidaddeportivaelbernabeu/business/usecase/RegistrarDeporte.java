package co.edu.uco.unidaddeportivaelbernabeu.business.usecase;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.DeporteDomain;

public interface RegistrarDeporte {
    void ejecutar(DeporteDomain deporte);
}
