package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
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
            var mensajeUsuario = "El nombre del tipo de espacio deportivo es obligatorio, por favor ingrese un nombre valido";
            var mensajeTecnico ="El nombre del tipo de espacio deportivo es obligatorio, por favor ingrese un nombre valido ";
            throw new BusinessUDElBernabeuException(mensajeUsuario,mensajeTecnico);
        }

        if (tipoEspacioDeportivo.getNombre().length() > 20) {
            var mensajeUsuario = "El nombre del tipo de espacio no puede exceder los 20 caracteres, por favor intente nuevamente con un nombre valido";
            var mensajeTecnico ="El nombre del tipo de espacio deportivo no puede exceder los 20 caracteres, por favor intente nuevamente con un nombre valido  ";
            throw new BusinessUDElBernabeuException(mensajeUsuario,mensajeTecnico);
        }

        if (!tipoEspacioDeportivo.getNombre().matches("^[A-Za-z ]+$")) {
            var mensajeUsuario = "El nombre del tipo de espacio no puede tener letras y espacios, por favor intente nuevamente con un nombre valido";
            var mensajeTecnico ="El nombre del tipo de espacio no puede tener letras y espacios, por favor intente nuevamente con un nombre valido ";
            throw new BusinessUDElBernabeuException(mensajeUsuario,mensajeTecnico);
        }

        if (tipoEspacioDeportivo.getCantidad() < 0 || tipoEspacioDeportivo.getCantidad() > 49) {
            var mensajeUsuario = "La cantidad de espacios debe ser un numero que este entre 1 y 49";
            var mensajeTecnico ="La cantidad de espacios debe ser un numero que este entre 1 y 49 ";
            throw new BusinessUDElBernabeuException(mensajeUsuario,mensajeTecnico);
        }

    }

    private boolean existeEspacioDeportivo(TipoEspacioDeportivoEntity tipoEspacioDeportivoEntity) {
        var resultado = factory.getTipoEspacioDeportivoDAO().consultar(tipoEspacioDeportivoEntity);
        return !resultado.isEmpty();
    }
}
