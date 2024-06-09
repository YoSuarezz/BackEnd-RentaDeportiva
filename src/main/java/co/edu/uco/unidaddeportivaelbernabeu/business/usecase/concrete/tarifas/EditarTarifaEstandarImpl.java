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

public class EditarTarifaEstandarImpl implements UseCaseWithoutReturn<TarifaEstandarDomain> {

    private final DAOFactory factory;

    public EditarTarifaEstandarImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TarifaEstandarDomain tarifaEstandar) {
        //POL 01 Asegurar que los datos cumplan con reglas de obligatoriedad, formato, longitud y rango
        validarTarifa(tarifaEstandar);

        var tarifaEstandarEntity = TarifaEstandarEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tarifaEstandar);

        //POL 02 Asegurar que no exista una tarifa estandar vigente para un mismo tipo de espacio a excepcion de que sea la que se esta editando
        if (existeOtraTarifaParaDeporte(tarifaEstandar)) {
            var mensajeUsuario = "Ya existe otra tarifa estandar para el tipo de espacio deportivo al cual le quieres asignar la tarifa estandar.";
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        //POL 03 Asegurar que si exista el tipo de espacio deportivo al cual se le va a asignar la tarifa estandar en su edicion.
        if (!existeTipoEspacioDeportivo(tarifaEstandar.getTipoEspacioDeportivo().getId())) {
            var mensajeUsuario = "No existe el tipo de espacio deportivo al cual quieres aplicar la tarifa estandar.";
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        //POL 04 Asegurar que exista la tarifa estandar que se va a editar
        if (!existeTarifa(tarifaEstandar.getId())) {
            var mensajeUsuario = "No existe la tarifa estandar que se desea editar.";
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }

        factory.getTarifaEstandarDAO().actualizar(tarifaEstandarEntity);
    }

    private void validarTarifa(TarifaEstandarDomain tarifaEstandar) {
        if (tarifaEstandar.getId() <= 0) {
            var mensajeUsuario = "No existe la tarifa estandar que se desea editar";
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
    }

    private boolean existeOtraTarifaParaDeporte(TarifaEstandarDomain tarifaEstandar) {
        var tarifaEstandarDAO = factory.getTarifaEstandarDAO();
        List<TarifaEstandarEntity> tarifas = tarifaEstandarDAO.consultarPorTipoEspacioDeportivo(tarifaEstandar.getTipoEspacioDeportivo().getId());

        return tarifas.stream()
                .anyMatch(tarifa -> tarifa.getId() != tarifaEstandar.getId());
    }

    private boolean existeTipoEspacioDeportivo(int tipoEspacioDeportivoId) {
        var tipoEspacioDeportivoDAO = factory.getTipoEspacioDeportivoDAO();
        var criterio = TipoEspacioDeportivoEntity.build(tipoEspacioDeportivoId);
        List<TipoEspacioDeportivoEntity> tiposEspacio = tipoEspacioDeportivoDAO.consultar(criterio);

        return !tiposEspacio.isEmpty();
    }

    private boolean existeTarifa(int tarifaId) {
        var tarifaEstandarDAO = factory.getTarifaEstandarDAO();
        var criterio = TarifaEstandarEntity.build(tarifaId);
        List<TarifaEstandarEntity> tarifas = tarifaEstandarDAO.consultar(criterio);

        return !tarifas.isEmpty();
    }
}
