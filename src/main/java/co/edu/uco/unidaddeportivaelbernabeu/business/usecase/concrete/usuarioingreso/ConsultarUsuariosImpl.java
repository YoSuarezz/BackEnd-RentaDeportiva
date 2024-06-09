package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.usuarioingreso.UsuarioEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.usuarioingreso.UsuarioDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

import java.util.List;

public final class ConsultarUsuariosImpl implements UseCaseWithReturn<UsuarioDomain, List<UsuarioDomain>> {

    private final DAOFactory factory;

    public ConsultarUsuariosImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public final List<UsuarioDomain> ejecutar(final UsuarioDomain usuario) {
        var usuarioEntity = UsuarioEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(usuario);
        var resultados = factory.getUsuarioDAO().consultar(usuarioEntity);

        return UsuarioEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(resultados);
    }
}
