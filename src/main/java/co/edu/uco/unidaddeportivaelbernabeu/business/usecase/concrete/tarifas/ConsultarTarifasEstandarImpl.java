package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas.TarifaEstandarEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.TarifaEstandarDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;


import java.util.List;

public class ConsultarTarifasEstandarImpl implements UseCaseWithReturn<TarifaEstandarDomain, List<TarifaEstandarDomain>> {

    private final DAOFactory factory;

    public ConsultarTarifasEstandarImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<TarifaEstandarDomain> ejecutar(TarifaEstandarDomain tarifaEstandarDomain) {
        var tarifaEstandarEntity = TarifaEstandarEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tarifaEstandarDomain);
        var resultados = factory.getTarifaEstandarDAO().consultar(tarifaEstandarEntity);
        return TarifaEstandarEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(resultados);
    }
}
