package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.EliminarTipoEspacioDeportivoImpl;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;

public class EliminarTipoEspacioDeportivoFachadaImpl implements FacadeWithoutReturn<Integer> {

    private final DAOFactory factory;

    public EliminarTipoEspacioDeportivoFachadaImpl() {
        this.factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public void ejecutar(Integer id) {
        EliminarTipoEspacioDeportivoImpl useCase = new EliminarTipoEspacioDeportivoImpl(factory);
        useCase.ejecutar(id);
    }
}
