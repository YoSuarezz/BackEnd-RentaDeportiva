package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.RegistrarDeporte;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

public class RegistrarDeporteImpl implements RegistrarDeporte {

    private final DAOFactory factory;

    public RegistrarDeporteImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(DeporteDomain deporte) {
        // POL.1 Validar que los datos sean validados a nivel de tipo de dato, longitud, obligatoriedad, formato, rango.
        // POL.2 No debe existir un deporte con el mismo nombre.
        // POL.3 Guardar la informacion de el nuevo deporte.
    }
}
