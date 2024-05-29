package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.UnidadDeportivaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.entity.UnidadDeportivaEntity;

public final class UnidadDeportivaAzureSqlDAO extends SqlConnection implements UnidadDeportivaDAO {

    public UnidadDeportivaAzureSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<UnidadDeportivaEntity> consultar(UnidadDeportivaEntity entity) {
        var resultados = new ArrayList<UnidadDeportivaEntity>();

        var sql = new StringBuilder();
        sql.append("SELECT id, nombre, ciudad, direccion ");
        sql.append("FROM UnidadDeportiva ");
        sql.append("WHERE 1=1 ");

        if (entity.getId() > 0) {
            sql.append("AND id = ? ");
        }
        if (!entity.getNombre().isEmpty()) {
            sql.append("AND nombre LIKE ? ");
        }
        if (!entity.getCiudad().isEmpty()) {
            sql.append("AND ciudad LIKE ? ");
        }
        if (!entity.getDireccion().isEmpty()) {
            sql.append("AND direccion LIKE ? ");
        }

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            if (entity.getId() > 0) {
                preparedStatement.setInt(parameterIndex++, entity.getId());
            }
            if (!entity.getNombre().isEmpty()) {
                preparedStatement.setString(parameterIndex++, "%" + entity.getNombre() + "%");
            }
            if (!entity.getCiudad().isEmpty()) {
                preparedStatement.setString(parameterIndex++, "%" + entity.getCiudad() + "%");
            }
            if (!entity.getDireccion().isEmpty()) {
                preparedStatement.setString(parameterIndex++, "%" + entity.getDireccion() + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var unidadDeportivaEntity = UnidadDeportivaEntity.build(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("ciudad"),
                        resultSet.getString("direccion")
                );
                resultados.add(unidadDeportivaEntity);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
            var mensajeTecnico = "Se ha presentado un problema ejecutando la consulta de Unidades Deportivas en la base de datos.";
            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
        return resultados;
    }
}
