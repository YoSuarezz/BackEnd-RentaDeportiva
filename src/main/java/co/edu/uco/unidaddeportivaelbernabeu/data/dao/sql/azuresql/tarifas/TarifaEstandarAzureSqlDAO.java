package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.TarifaEstandarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.TarifaEstandarEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaEstandarAzureSqlDAO implements TarifaEstandarDAO {

    private final Connection connection;

    public TarifaEstandarAzureSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(TarifaEstandarEntity entidad) {
        final String sql = "INSERT INTO TarifaEstandar (tipoEspacioDeportivoId, precioPorHora, nombre, fechaHoraInicio, fechaHoraFin) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entidad.getTipoEspacioDeportivo().getId());
            statement.setInt(2, entidad.getPrecioPorHora());
            statement.setString(3, entidad.getNombre());
            statement.setTimestamp(4, Timestamp.valueOf(entidad.getFechaHoraInicio().withSecond(0).withNano(0)));
            statement.setTimestamp(5, Timestamp.valueOf(entidad.getFechaHoraFin().withSecond(0).withNano(0)));

            statement.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00099);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public List<TarifaEstandarEntity> consultarPorTipoEspacioDeportivo(int tipoEspacioDeportivoId) {
        final String sql = "SELECT id, tipoEspacioDeportivoId, nombre, precioPorHora, fechaHoraInicio, fechaHoraFin FROM TarifaEstandar WHERE tipoEspacioDeportivoId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tipoEspacioDeportivoId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<TarifaEstandarEntity> tarifas = new ArrayList<>();
                while (resultSet.next()) {
                    tarifas.add(TarifaEstandarEntity.build(
                            resultSet.getInt("id"),
                            TipoEspacioDeportivoEntity.build(resultSet.getInt("tipoEspacioDeportivoId")),
                            resultSet.getString("nombre"),
                            resultSet.getInt("precioPorHora"),
                            resultSet.getTimestamp("fechaHoraInicio").toLocalDateTime(),
                            resultSet.getTimestamp("fechaHoraFin").toLocalDateTime()
                    ));
                }
                return tarifas;
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00100);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }
}
