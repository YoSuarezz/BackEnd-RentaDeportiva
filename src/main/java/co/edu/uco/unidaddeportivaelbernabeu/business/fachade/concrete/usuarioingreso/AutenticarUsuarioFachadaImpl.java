package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.usuarioingreso.UsuarioDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.usuarioingreso.AutenticarUsuarioImpl;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;

public final class AutenticarUsuarioFachadaImpl implements FacadeWithReturn<UsuarioDTO, Boolean> {

    private DAOFactory factory;

    public AutenticarUsuarioFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public final Boolean ejecutar(final UsuarioDTO usuario) {
        factory.iniciarTransaccion();
        try {
            var usuarioDomain = UsuarioDTODomainAssembler.obtenerInstancia().ensamblarDominio(usuario);

            final AutenticarUsuarioImpl useCase = new AutenticarUsuarioImpl(factory);
            return useCase.ejecutar(usuarioDomain);
        } catch (UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            factory.cancelarTransaccion();
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00130);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00131);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
