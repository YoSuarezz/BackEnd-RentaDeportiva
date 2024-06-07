package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas.TarifaEstandarEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.TarifaEstandarDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
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
            throw new BusinessUDElBernabeuException("Ya existe una tarifa estándar para este deporte.");
        }

        factory.getTarifaEstandarDAO().crear(tarifaEstandarEntity);
    }

    private void validarTarifa(TarifaEstandarDomain tarifaEstandar) {
        if (tarifaEstandar.getId() < 0) {
            throw new BusinessUDElBernabeuException("El ID debe ser positivo.");
        }
        if (tarifaEstandar.getPrecioPorHora() <= 0) {
            throw new BusinessUDElBernabeuException("El precio por hora debe ser positivo.");
        }
        if (tarifaEstandar.getNombre() == null || tarifaEstandar.getNombre().trim().isEmpty()) {
            throw new BusinessUDElBernabeuException("El nombre no puede ser nulo o vacío.");
        }
        if (tarifaEstandar.getFechaHoraInicio() == null) {
            throw new BusinessUDElBernabeuException("La fecha y hora de inicio no pueden ser nulas.");
        }
        if (tarifaEstandar.getFechaHoraFin() == null) {
            throw new BusinessUDElBernabeuException("La fecha y hora de fin no pueden ser nulas.");
        }
        if (tarifaEstandar.getFechaHoraInicio().isAfter(tarifaEstandar.getFechaHoraFin())) {
            throw new BusinessUDElBernabeuException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
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
