package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
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

        //Implementacion para Validacion de la primer politica
        if (!existenciaEspacioDeportivo(tipoEspacioDeportivoEntity)) {

            var mensajeUsuario = "No existe el tipo de espacio deportivo que se desea editar ";
            var mensajeTecnico = "No existe el tipo de espacio deportivo que se desea editar, por favor intente con uno nuevo que si exista en la base de datos";
            throw new BusinessUDElBernabeuException(mensajeUsuario, mensajeTecnico);
        }

        //Implementacion para la validacion de la segunda politica
        validarDatos(tipoEspacioDeportivo);

        //Implementacion para la validacion de la tercer politica
        if (nombreDuplicado(tipoEspacioDeportivoEntity)){
            var mensajeUsuario = "No es posible establecer el nombre debido a que ya hay otro tipo de espacio deportivo con el mismo nombre ";
            var mensajeTecnico ="No es posible establecer el nombre debido a que ya hay otro tipo de espacio deportivo con el mismo nombre ";
            throw new BusinessUDElBernabeuException(mensajeUsuario,mensajeTecnico);
        }

        factory.getTipoEspacioDeportivoDAO().actualizar(tipoEspacioDeportivoEntity);
    }
    //Pol Validar que los datos a editar cumplan con reglas de obligatoriedad, formato, longitud y rango
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


    //Debe existir el tipo de espacio deportivo que se desea editar
    private boolean existenciaEspacioDeportivo(TipoEspacioDeportivoEntity tipoEspacioDeportivo) {
        var resultado = factory.getTipoEspacioDeportivoDAO().consultar(TipoEspacioDeportivoEntity.build(tipoEspacioDeportivo.getId()));
        return !resultado.isEmpty();
    }

    //No debe existir mas de un tipo de espacio deportivo con el mismo nombre, a excepcion del que se esta editando
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
