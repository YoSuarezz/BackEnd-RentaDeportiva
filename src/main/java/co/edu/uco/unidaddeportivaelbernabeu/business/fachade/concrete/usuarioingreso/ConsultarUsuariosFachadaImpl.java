package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.usuarioingreso.UsuarioDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.usuarioingreso.ConsultarUsuariosImpl;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;

import java.util.List;

public final class ConsultarUsuariosFachadaImpl implements FacadeWithReturn<UsuarioDTO, List<UsuarioDTO>> {

    private DAOFactory factory;

    public ConsultarUsuariosFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public final List<UsuarioDTO> ejecutar(final UsuarioDTO usuario) {
        factory.iniciarTransaccion();
        try {
            var usuarioDomain = UsuarioDTODomainAssembler.obtenerInstancia().ensamblarDominio(usuario);

            final ConsultarUsuariosImpl useCase = new ConsultarUsuariosImpl(factory);
            var resultados = useCase.ejecutar(usuarioDomain);
            return UsuarioDTODomainAssembler.obtenerInstancia().ensamblarListaDTO(resultados);
        } catch (UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            factory.cancelarTransaccion();
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion del usuario";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar la informacion del usuario";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
