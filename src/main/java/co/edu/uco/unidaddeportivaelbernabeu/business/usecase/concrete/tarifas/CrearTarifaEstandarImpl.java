package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas.TarifaEstandarEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.TarifaEstandarDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

public class CrearTarifaEstandarImpl implements UseCaseWithoutReturn<TarifaEstandarDomain> {

    private final DAOFactory factory;


    public CrearTarifaEstandarImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public void ejecutar(TarifaEstandarDomain tarifaEstandar) {
        var tarifaEstandarEntity = TarifaEstandarEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tarifaEstandar);
        factory.getTarifaEstandarDAO().crear(tarifaEstandarEntity);
    }
}
