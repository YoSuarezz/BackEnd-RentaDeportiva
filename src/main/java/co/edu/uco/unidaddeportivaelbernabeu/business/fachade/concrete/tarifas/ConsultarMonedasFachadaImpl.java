package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas.MonedaDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas.ConsultarMonedasImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.MonedaDTO;

import java.util.List;

public class ConsultarMonedasFachadaImpl implements UseCaseWithReturn<MonedaDTO, List<MonedaDTO>> {

    private DAOFactory factory;

    public ConsultarMonedasFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }


    @Override
    public List<MonedaDTO> ejecutar(final MonedaDTO moneda) {
        factory.iniciarTransaccion();
        try {
            var monedaDomain = MonedaDTODomainAssembler.obtenerInstancia().ensamblarDominio(moneda);

            final ConsultarMonedasImpl useCase = new ConsultarMonedasImpl(factory);
            var resultados = useCase.ejecutar(monedaDomain);
            return MonedaDTODomainAssembler.obtenerInstancia()
                    .ensamblarListaDTO(resultados);

        }catch (UnidadDeportivaElBernabeuException exception){
            factory.cancelarTransaccion();
            throw exception;
        }catch (Exception exception){
            factory.cancelarTransaccion();
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de las monedas.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar la informacion de las monedas.";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
