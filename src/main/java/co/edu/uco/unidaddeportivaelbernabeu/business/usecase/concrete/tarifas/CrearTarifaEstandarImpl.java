package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas.TarifaEstandarEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.TarifaEstandarDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.TarifaEstandarEntity;

import java.util.List;

public class CrearTarifaEstandarImpl implements UseCaseWithoutReturn<TarifaEstandarDomain> {

    private final DAOFactory factory;

    public CrearTarifaEstandarImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TarifaEstandarDomain tarifaEstandar) {
        validarTarifa(tarifaEstandar);

        var tarifaEstandarEntity = TarifaEstandarEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tarifaEstandar);

        if (existeTarifaParaDeporte(tarifaEstandarEntity.getTipoEspacioDeportivo().getId())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00084);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (!existeTipoEspacioDeportivo(tarifaEstandarEntity.getTipoEspacioDeportivo().getId())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00101);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        factory.getTarifaEstandarDAO().crear(tarifaEstandarEntity);
    }

    private void validarTarifa(TarifaEstandarDomain tarifaEstandar) {
        if (tarifaEstandar.getTipoEspacioDeportivo() == null || tarifaEstandar.getTipoEspacioDeportivo().getId() <= 0) {
            var mensajeUsuario = "Debe seleccionar un tipo de espacio deportivo";
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        if (tarifaEstandar.getPrecioPorHora() <= 0) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00086);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        if (tarifaEstandar.getNombre() == null || tarifaEstandar.getNombre().trim().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00087);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        if (tarifaEstandar.getFechaHoraInicio() == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00088);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        if (tarifaEstandar.getFechaHoraFin() == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00089);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        if (tarifaEstandar.getFechaHoraInicio().isAfter(tarifaEstandar.getFechaHoraFin())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00090);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        if (tarifaEstandar.getNombre().length() > 50) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00104);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        if (tarifaEstandar.getMoneda() == null || tarifaEstandar.getMoneda().getId() <= 0) {
            var mensajeUsuario = "La moneda es obligatoria y debe ser válida.";
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
    }

    private boolean existeTipoEspacioDeportivo(int tipoEspacioDeportivoId) {
        var tipoEspacioDeportivoDAO = factory.getTipoEspacioDeportivoDAO();
        var criterio = TipoEspacioDeportivoEntity.build(tipoEspacioDeportivoId);
        List<TipoEspacioDeportivoEntity> tiposEspacio = tipoEspacioDeportivoDAO.consultar(criterio);

        return !tiposEspacio.isEmpty();
    }

    private boolean existeTarifaParaDeporte(int tipoEspacioDeportivoId) {
        var tipoEspacioDeportivoDAO = factory.getTipoEspacioDeportivoDAO();
        var criterio = TipoEspacioDeportivoEntity.build(tipoEspacioDeportivoId);
        List<TipoEspacioDeportivoEntity> tiposEspacio = tipoEspacioDeportivoDAO.consultar(criterio);

        if (tiposEspacio.isEmpty()) {
            return false;
        }

        var tarifaEstandarDAO = factory.getTarifaEstandarDAO();
        List<TarifaEstandarEntity> tarifas = tarifaEstandarDAO.consultarPorTipoEspacioDeportivo(tipoEspacioDeportivoId);

        return !tarifas.isEmpty();
    }
}
