package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas.TarifaEstandarDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas.CrearTarifaEstandarImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
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

            var mensajeUsuario = "Se ha presentado un problema tratando de crear la tarifa Estandar";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de crear la informacion de la nueva tarifa estandar en el metodo ejecutar de la clase CrearTarifaEstandarFachadaImpl. Por favor revise la traza completa del problema";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            factory.cerrarConexion();
        }
    }
}
