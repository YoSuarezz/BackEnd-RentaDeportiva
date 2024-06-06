package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;

public class RegistrarTipoEspacioDeportivoImpl implements UseCaseWithoutReturn<TipoEspacioDeportivoDomain> {

    private final DAOFactory factory;

    public RegistrarTipoEspacioDeportivoImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        validarDatos(tipoEspacioDeportivo);

        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivo);

        if (existeEspacioDeportivo(tipoEspacioDeportivoEntity)) {
            throw new IllegalStateException("Ya existe un tipo de espacio deportivo con el mismo nombre.");
        }

        factory.getTipoEspacioDeportivoDAO().crear(tipoEspacioDeportivoEntity);
    }

    private void validarDatos(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
            if (tipoEspacioDeportivo.getNombre() == null || tipoEspacioDeportivo.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de espacio deportivo es obligatorio.");
        }

        // Validación de la longitud del nombre
        if (tipoEspacioDeportivo.getNombre().length() > 20) {
            throw new IllegalArgumentException("El nombre del tipo de espacio deportivo no puede exceder los 20 caracteres.");
        }

        // Validación de formato para el nombre utilizando expresiones regulares (solo letras y espacios, por ejemplo)
        if (!tipoEspacioDeportivo.getNombre().matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException("El nombre del tipo de espacio deportivo solo puede contener letras y espacios.");
        }

        // Validación de rango para la cantidad
        if (tipoEspacioDeportivo.getCantidad() <= 0 || tipoEspacioDeportivo.getCantidad() > 49) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero y menor o igual que 49.");
        }
    }

    private boolean existeEspacioDeportivo(TipoEspacioDeportivoEntity tipoEspacioDeportivoEntity) {
        var resultado = factory.getTipoEspacioDeportivoDAO().consultar(tipoEspacioDeportivoEntity);
        return !resultado.isEmpty();
    }
}
