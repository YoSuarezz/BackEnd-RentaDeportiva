package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.usuarioingreso.UsuarioDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.usuarioingreso.ConsultarUsuarioImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;

import java.util.List;

public class ConsultarUsuarioFachadaImpl implements UseCaseWithReturn<UsuarioDTO, List<UsuarioDTO>> {

    private DAOFactory factory;

    public ConsultarUsuarioFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }


    @Override
    public List<UsuarioDTO> ejecutar(final UsuarioDTO usuario) {
        try {
            var usuarioDomain = UsuarioDTODomainAssembler.obtenerInstancia().ensamblarDominio(usuario);

            final ConsultarUsuarioImpl useCase = new ConsultarUsuarioImpl(factory);
            var resultados = useCase.ejecutar(usuarioDomain);
            return UsuarioDTODomainAssembler.obtenerInstancia()
                    .ensamblarListaDTO(resultados);

        }catch (UnidadDeportivaElBernabeuException exception){
            factory.cancelarTransaccion();
            throw exception;
        }catch (Exception exception){
            factory.cancelarTransaccion();
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de el usuario";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar la informacion del usuario";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
