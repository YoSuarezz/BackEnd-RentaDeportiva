package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.espaciosdeportivos.DeporteEntity;
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
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00070);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (!existeDeporte(tipoEspacioDeportivoEntity.getDeporte().getId())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00105);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        factory.getTipoEspacioDeportivoDAO().crear(tipoEspacioDeportivoEntity);
    }

    private void validarDatos(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        if (tipoEspacioDeportivo.getNombre() == null || tipoEspacioDeportivo.getNombre().trim().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00071);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (tipoEspacioDeportivo.getNombre().length() > 20) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00072);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (!tipoEspacioDeportivo.getNombre().matches("^[A-Za-z ]+$")) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00073);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (tipoEspacioDeportivo.getCantidad() < 0 || tipoEspacioDeportivo.getCantidad() > 49) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00074);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

    }

    private boolean existeEspacioDeportivo(TipoEspacioDeportivoEntity tipoEspacioDeportivoEntity) {
        var resultado = factory.getTipoEspacioDeportivoDAO().consultar(tipoEspacioDeportivoEntity);
        return !resultado.isEmpty();
    }

    private boolean existeDeporte(int deporteId) {
        var deporteDAO = factory.getDeporteDAO();
        var deporte = DeporteEntity.build(deporteId);
        var resultado = deporteDAO.consultar(deporte);
        return !resultado.isEmpty();
    }
}
