package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.espaciosdeportivos.UnidadDeportivaEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

import java.util.List;

public final class ConsultarUnidadesDeportivasImpl implements UseCaseWithReturn<UnidadDeportivaDomain, List<UnidadDeportivaDomain>> {

    private final DAOFactory factory;

    public ConsultarUnidadesDeportivasImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<UnidadDeportivaDomain> ejecutar(UnidadDeportivaDomain unidadDeportiva) {
        var unidadDeportivaEntity = UnidadDeportivaEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(unidadDeportiva);
        var resultados = factory.getUnidadDeportivaDAO().consultar(unidadDeportivaEntity);

        return UnidadDeportivaEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(resultados);
    }
}
