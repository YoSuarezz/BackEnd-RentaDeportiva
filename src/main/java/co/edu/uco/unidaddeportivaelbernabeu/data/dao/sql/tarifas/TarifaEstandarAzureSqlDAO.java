package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.DateHelper;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.TipoEspacioDeportivoDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.TarifaEstandarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.TarifaEstandarEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TarifaEstandarAzureSqlDAO implements TarifaEstandarDAO {

    private final Connection connection;

    public TarifaEstandarAzureSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(TarifaEstandarEntity entidad) {
        DateHelper.validateDates(entidad.getFechaHoraInicio(), entidad.getFechaHoraFin());

        final String sql = "INSERT INTO TarifaEstandar (tipoEspacioDeportivoId, precioPorHora, nombre, fechaHoraInicio, fechaHoraFin) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entidad.getTipoEspacioDeportivo().getId());
            statement.setInt(2, entidad.getPrecioPorHora());
            statement.setString(3, entidad.getNombre());
            statement.setTimestamp(4, Timestamp.valueOf(entidad.getFechaHoraInicio()));
            statement.setTimestamp(5, Timestamp.valueOf(entidad.getFechaHoraFin()));

            statement.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presnetado un problema intentando hacer la operacion deseada";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO trantando de ingresar los datos de la tarifa estandar a la base de datos";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    private TipoEspacioDeportivoEntity obtenerTipoEspacioDeportivo(int tipoEspacioDeportivoId){
        TipoEspacioDeportivoDAO tipoEspacioDeportivoDAO = DAOFactory.getFactory(Factory.AZURE_SQL).getTipoEspacioDeportivoDAO();
        TipoEspacioDeportivoEntity criterio = TipoEspacioDeportivoEntity.build(tipoEspacioDeportivoId);
        List<TipoEspacioDeportivoEntity> tipoEspacioDeportivo = tipoEspacioDeportivoDAO.consultar(criterio);
        if(!tipoEspacioDeportivo.isEmpty()){
            return tipoEspacioDeportivo.get(0);
        }
        return null;
    }
}
