package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas.TarifaEstandarDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas.EditarTarifaEstandarImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.TarifaEstandarDTO;

public class EditarTarifaEstandarFachadaImpl implements FacadeWithoutReturn<TarifaEstandarDTO> {

    private DAOFactory factory;

    public EditarTarifaEstandarFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public void ejecutar(TarifaEstandarDTO tarifaEstandar) {
        factory.iniciarTransaccion();

        try {
            var usecase = new EditarTarifaEstandarImpl(factory);
            var tarifaEstandarDomain = TarifaEstandarDTODomainAssembler.obtenerInstancia().ensamblarDominio(tarifaEstandar);
            usecase.ejecutar(tarifaEstandarDomain);

            factory.confirmarTransaccion();
        } catch (final UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (final Exception exception) {
            factory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de editar la tarifa Estandar";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de editar la informacion de la tarifa estandar en el metodo ejecutar de la clase EditarTarifaEstandarFachadaImpl. Por favor revise la traza completa del problema";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            factory.cerrarConexion();
        }
    }
}
