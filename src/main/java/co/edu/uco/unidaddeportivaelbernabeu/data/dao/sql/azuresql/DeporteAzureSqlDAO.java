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
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;

public final class DeporteAzureSqlDAO extends SqlConnection implements DeporteDAO {

    public DeporteAzureSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<DeporteEntity> consultar(DeporteEntity entidad) {
        List<DeporteEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nombre FROM Deporte WHERE 1=1 ");

        List<Object> parametros = new ArrayList<>();

        if (entidad.getId() > 0) {
            sql.append("AND Id = ? ");
            parametros.add(entidad.getId());
        }

        if (entidad.getNombre() != null && !entidad.getNombre().isEmpty()) {
            sql.append("AND Nombre LIKE ? ");
            parametros.add("%" + entidad.getNombre() + "%");
        }

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                preparedStatement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    DeporteEntity deporte = new DeporteEntity(
                            resultSet.getInt("Id"),
                            resultSet.getString("Nombre")
                    );
                    resultados.add(deporte);
                }
            }
        } catch (SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003);
            var mensajeTecnico = "Se ha presentado un problema consultando los datos de Deporte en la base de datos.";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, excepcion);
        }

        return resultados;
    }
}
