package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.TarifaEstandarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.MonedaEntity;
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
        final String sql = "INSERT INTO TarifaEstandar (tipoEspacioDeportivoId, precioPorHora, nombre, fechaHoraInicio, fechaHoraFin, monedaId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entidad.getTipoEspacioDeportivo().getId());
            statement.setInt(2, entidad.getPrecioPorHora());
            statement.setString(3, entidad.getNombre());
            statement.setTimestamp(4, Timestamp.valueOf(entidad.getFechaHoraInicio().withSecond(0).withNano(0)));
            statement.setTimestamp(5, Timestamp.valueOf(entidad.getFechaHoraFin().withSecond(0).withNano(0)));
            statement.setInt(6, entidad.getMoneda().getId());

            statement.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00099);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public List<TarifaEstandarEntity> consultarPorTipoEspacioDeportivo(int tipoEspacioDeportivoId) {
        final String sql = "SELECT id, tipoEspacioDeportivoId, nombre, precioPorHora, fechaHoraInicio, fechaHoraFin, monedaId FROM TarifaEstandar WHERE tipoEspacioDeportivoId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tipoEspacioDeportivoId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<TarifaEstandarEntity> tarifas = new ArrayList<>();
                while (resultSet.next()) {
                    tarifas.add(TarifaEstandarEntity.build(
                            resultSet.getInt("id"),
                            TipoEspacioDeportivoEntity.build(resultSet.getInt("tipoEspacioDeportivoId")),
                            MonedaEntity.build(resultSet.getInt("monedaId")),
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

    @Override
    public void actualizar(TarifaEstandarEntity entidad) {
        final String sql = "UPDATE TarifaEstandar SET tipoEspacioDeportivoId = ?, precioPorHora = ?, nombre = ?, fechaHoraInicio = ?, fechaHoraFin = ?, monedaId = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entidad.getTipoEspacioDeportivo().getId());
            statement.setInt(2, entidad.getPrecioPorHora());
            statement.setString(3, entidad.getNombre());
            statement.setTimestamp(4, Timestamp.valueOf(entidad.getFechaHoraInicio().withSecond(0).withNano(0)));
            statement.setTimestamp(5, Timestamp.valueOf(entidad.getFechaHoraFin().withSecond(0).withNano(0)));
            statement.setInt(6, entidad.getMoneda().getId());
            statement.setInt(7, entidad.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataUDElBernabeuException("No se encontró la tarifa estándar con el ID especificado.");
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de actualizar las tarifas estándar en la base de datos.";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }
    @Override
    public List<TarifaEstandarEntity> consultar(TarifaEstandarEntity criterio) {
        List<TarifaEstandarEntity> resultados = new ArrayList<>();
        String sql = "SELECT id, tipoEspacioDeportivoId, monedaId , nombre, precioPorHora, fechaHoraInicio, fechaHoraFin FROM TarifaEstandar";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TarifaEstandarEntity tarifa = new TarifaEstandarEntity(
                        resultSet.getInt("id"),
                        TipoEspacioDeportivoEntity.build(resultSet.getInt("tipoEspacioDeportivoId")),
                        MonedaEntity.build(resultSet.getInt("monedaId")),
                        resultSet.getString("nombre"),
                        resultSet.getInt("precioPorHora"),
                        resultSet.getTimestamp("fechaHoraInicio").toLocalDateTime(),
                        resultSet.getTimestamp("fechaHoraFin").toLocalDateTime()
                );
                resultados.add(tarifa);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00100);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
        return resultados;
    }
}