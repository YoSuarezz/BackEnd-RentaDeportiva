package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.RegistrarTipoEspacioDeportivo;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.TipoEspacioDeportivoDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;

import java.util.List;

public class RegistrarTipoEspacioDeportivoImpl implements RegistrarTipoEspacioDeportivo {

    private final DAOFactory factory;

    public RegistrarTipoEspacioDeportivoImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {

    }
}