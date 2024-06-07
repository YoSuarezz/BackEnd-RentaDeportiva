package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas.TarifaEstandarDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas.CrearTarifaEstandarImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.TarifaEstandarDTO;

public class CrearTarifaEstandarFachadaImpl implements FacadeWithoutReturn<TarifaEstandarDTO> {

    private DAOFactory factory;

    public CrearTarifaEstandarFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public void ejecutar(TarifaEstandarDTO tarifaEstandar) {
        factory.iniciarTransaccion();

        try {
            var usecase = new CrearTarifaEstandarImpl(factory);
            var tarifaEstandarDomain = TarifaEstandarDTODomainAssembler.obtenerInstancia().ensamblarDominio(tarifaEstandar);
            usecase.ejecutar(tarifaEstandarDomain);

            factory.confirmarTransaccion();
        } catch (final UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (final Exception exception) {
            factory.cancelarTransaccion();

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00093);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00094);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            factory.cerrarConexion();
        }
    }
}
