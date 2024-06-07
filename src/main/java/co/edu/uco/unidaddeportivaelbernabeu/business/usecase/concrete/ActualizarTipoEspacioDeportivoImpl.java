package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;

import java.util.List;

public class ActualizarTipoEspacioDeportivoImpl implements UseCaseWithoutReturn<TipoEspacioDeportivoDomain> {

    private final DAOFactory factory;

    public ActualizarTipoEspacioDeportivoImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivo);

        if (!existenciaEspacioDeportivo(tipoEspacioDeportivoEntity)) {
            throw new IllegalArgumentException("No existe el tipo de espacio deportivo que se desea editar");
        }

        validarDatos(tipoEspacioDeportivo);

        factory.getTipoEspacioDeportivoDAO().actualizar(tipoEspacioDeportivoEntity);
    }

    private void validarDatos(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        if (tipoEspacioDeportivo.getNombre() == null || tipoEspacioDeportivo.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de espacio deportivo es obligatorio.");
        }

        if (tipoEspacioDeportivo.getNombre().length() > 20) {
            throw new IllegalArgumentException("El nombre del tipo de espacio deportivo no puede exceder los 20 caracteres.");
        }

        if (!tipoEspacioDeportivo.getNombre().matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException("El nombre del tipo de espacio deportivo solo puede contener letras y espacios.");
        }

        if (tipoEspacioDeportivo.getCantidad() <= 0 || tipoEspacioDeportivo.getCantidad() > 49) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero y menor o igual que 49.");
        }

        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivo);

        if (nombreDuplicado(tipoEspacioDeportivoEntity)) {
            throw new IllegalArgumentException("Ya existe un tipo de espacio deportivo con el mismo nombre.");
        }
    }

    private boolean existenciaEspacioDeportivo(TipoEspacioDeportivoEntity tipoEspacioDeportivo) {
        var resultado = factory.getTipoEspacioDeportivoDAO().consultar(TipoEspacioDeportivoEntity.build(tipoEspacioDeportivo.getId()));
        return !resultado.isEmpty();
    }

    private boolean nombreDuplicado(TipoEspacioDeportivoEntity tipoEspacioDeportivoEntity) {
        List<TipoEspacioDeportivoEntity> espacios = factory.getTipoEspacioDeportivoDAO().consultar(TipoEspacioDeportivoEntity.build());
        for (TipoEspacioDeportivoEntity espacio : espacios) {
            if (espacio.getNombre().equalsIgnoreCase(tipoEspacioDeportivoEntity.getNombre()) && espacio.getId() != tipoEspacioDeportivoEntity.getId()) {
                return true;
            }
        }
        return false;
    }
}
