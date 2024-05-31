package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

public class  RegistrarTipoEspacioDeportivoImpl implements UseCaseWithoutReturn<TipoEspacioDeportivoDomain> {

    private final DAOFactory factory;

    public RegistrarTipoEspacioDeportivoImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivo);
        factory.getTipoEspacioDeportivoDAO().crear(tipoEspacioDeportivoEntity);
    }
}