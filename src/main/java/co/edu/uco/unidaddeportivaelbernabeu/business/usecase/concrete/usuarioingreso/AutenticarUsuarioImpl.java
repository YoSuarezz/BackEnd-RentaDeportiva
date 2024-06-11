package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.usuarioingreso.UsuarioDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

public final class AutenticarUsuarioImpl implements UseCaseWithReturn<UsuarioDomain, Boolean> {

    private final DAOFactory factory;

    public AutenticarUsuarioImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public Boolean ejecutar(final UsuarioDomain usuario) {

        if (usuario.getUsuario().isEmpty() && usuario.getContrasena().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00118);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (usuario.getUsuario().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00119);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (usuario.getContrasena().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00120);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        var usuarioDAO = factory.getUsuarioDAO();
        boolean isAuthenticated = usuarioDAO.autenticarUsuario(usuario.getUsuario(), usuario.getContrasena());

        if (!isAuthenticated) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00121);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        return true;
    }
}
