package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
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
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00078);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        //Implementacion para la validacion de la segunda politica
        validarDatos(tipoEspacioDeportivo);

        //Implementacion para la validacion de la tercer politica
        if (nombreDuplicado(tipoEspacioDeportivoEntity)){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00079);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        factory.getTipoEspacioDeportivoDAO().actualizar(tipoEspacioDeportivoEntity);
    }
    //Pol Validar que los datos a editar cumplan con reglas de obligatoriedad, formato, longitud y rango
    private void validarDatos(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {

        // Que los datos ingresados no sean vacios
        if (tipoEspacioDeportivo.getNombre().isEmpty() ||
                tipoEspacioDeportivo.getNombre() == null ||
                tipoEspacioDeportivo.getEspacio().trim().isEmpty()||
                tipoEspacioDeportivo.getEspacio() == null)  {

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00080);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }


        //Que el nombre no exceda los 20 caracteres ¿
        if (tipoEspacioDeportivo.getNombre().length() > 20) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00081);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        //Que el nombre solo contenga letras y espacios
        if (!tipoEspacioDeportivo.getNombre().matches("^[A-Za-z ]+$")) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00082);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        //Que la cantidad de espacios este entre 1 y 49
        if (tipoEspacioDeportivo.getCantidad() <= 0 || tipoEspacioDeportivo.getCantidad() > 49) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
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
