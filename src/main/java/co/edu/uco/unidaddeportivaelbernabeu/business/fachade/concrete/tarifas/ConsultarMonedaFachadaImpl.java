package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.espaciosdeportivos.DeporteDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.espaciosdeportivos.ConsultarDeportesImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.MonedaDTO;

import java.util.List;

public class ConsultarMonedaFachadaImpl implements UseCaseWithReturn<MonedaDTO, List<MonedaDTO>> {

    private DAOFactory factory;

    public ConsultarMonedaFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }


    @Override
    public List<MonedaDTO> ejecutar(final MonedaDTO moneda) {

        return List.of();
    }
}
