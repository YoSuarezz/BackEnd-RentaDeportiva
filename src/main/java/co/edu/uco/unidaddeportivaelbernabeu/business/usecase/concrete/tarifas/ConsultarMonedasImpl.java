package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.MonedaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas.MonedaEntityDomainAssembler;
import java.util.List;

public final class ConsultarMonedasImpl implements UseCaseWithReturn<MonedaDomain, List<MonedaDomain>> {

    private final DAOFactory factory;

    public ConsultarMonedasImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public final List<MonedaDomain> ejecutar(final MonedaDomain moneda) {
        var monedaEntity = MonedaEntityDomainAssembler.obtenerIntancia().ensamblarEntidad(moneda);
        var resultados = factory.getMonedaDAO().consultar(monedaEntity);

        return MonedaEntityDomainAssembler.obtenerIntancia().ensamblarListaDominios(resultados);
    }
}
