package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.SQLHelper;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.TipoEspacioDeportivoDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.UnidadDeportivaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.UnidadDeportivaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoEspacioDeportivoAzureSqlDAO implements TipoEspacioDeportivoDAO {

    private final Connection connection;

    public TipoEspacioDeportivoAzureSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(TipoEspacioDeportivoEntity entidad) {
        final String sql = "INSERT INTO TipoEspacioDeportivo (UnidadDeportiva, Deporte, Espacio, Cantidad, Nombre) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entidad.getUnidadDeportiva().getId());
            statement.setInt(2, entidad.getDeporte().getId());
            statement.setString(3, entidad.getEspacio());
            statement.setInt(4, entidad.getCantidad());
            statement.setString(5, entidad.getNombre());

            statement.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00061);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public void actualizar(TipoEspacioDeportivoEntity entidad) {
        final String sql = "UPDATE TipoEspacioDeportivo SET UnidadDeportiva = ?, Deporte = ?, Espacio = ?, " +
                "Cantidad = ?, Nombre = ? WHERE Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entidad.getUnidadDeportiva().getId());
            statement.setInt(2, entidad.getDeporte().getId());
            statement.setString(3, entidad.getEspacio());
            statement.setInt(4, entidad.getCantidad());
            statement.setString(5, entidad.getNombre());
            statement.setInt(6, entidad.getId());

            statement.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00062);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public void eliminar(int id) {
        final String sql = "DELETE FROM TipoEspacioDeportivo WHERE Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003);
            var mensajeTecnico = "Se ha presentado un problema consultando los datos de Unidad Deportiva en la base de datos.";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public List<TipoEspacioDeportivoEntity> consultar(TipoEspacioDeportivoEntity entidad) {
        final String sql = "SELECT Id, UnidadDeportiva, Deporte, Espacio, Cantidad, Nombre FROM TipoEspacioDeportivo";
        List<TipoEspacioDeportivoEntity> resultados = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int unidadDeportivaId = resultSet.getInt("UnidadDeportiva");
                int deporteId = resultSet.getInt("Deporte");
                String espacio = resultSet.getString("Espacio");
                int cantidad = resultSet.getInt("Cantidad");
                String nombre = resultSet.getString("Nombre");

                UnidadDeportivaEntity unidadDeportiva = obtenerUnidadDeportiva(unidadDeportivaId);
                DeporteEntity deporte = obtenerDeporte(deporteId);

                TipoEspacioDeportivoEntity tipoEspacioDeportivo = new TipoEspacioDeportivoEntity(
                        id,
                        unidadDeportiva,
                        deporte,
                        espacio,
                        cantidad,
                        nombre
                );
                resultados.add(tipoEspacioDeportivo);
            }
            return resultados;
        } catch (SQLException exception) {
            var mensajeUsuario = "";
            var mensajeTecnico = "Se ha presentado un problema consultando los datos de los tipos de espacios deportivos.";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    private UnidadDeportivaEntity obtenerUnidadDeportiva(int unidadDeportivaId) {
        UnidadDeportivaDAO unidadDeportivaDAO = DAOFactory.getFactory(Factory.AZURE_SQL).getUnidadDeportivaDAO();
        UnidadDeportivaEntity criterio = UnidadDeportivaEntity.build(unidadDeportivaId);
        List<UnidadDeportivaEntity> unidadesDeportivas = unidadDeportivaDAO.consultar(criterio);
        if (!unidadesDeportivas.isEmpty()) {
            return unidadesDeportivas.get(0);
        }
        return null;
    }

    private DeporteEntity obtenerDeporte(int deporteId) {
        DeporteDAO deporteDAO = DAOFactory.getFactory(Factory.AZURE_SQL).getDeporteDAO();
        DeporteEntity criterio = DeporteEntity.build(deporteId);
        List<DeporteEntity> deportes = deporteDAO.consultar(criterio);
        if (!deportes.isEmpty()) {
            return deportes.get(0);
        }
        return null;
    }
}
