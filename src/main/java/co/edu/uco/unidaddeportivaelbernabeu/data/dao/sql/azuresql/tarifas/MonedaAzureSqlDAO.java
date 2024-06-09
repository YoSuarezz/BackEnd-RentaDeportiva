package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.MonedaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.MonedaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonedaAzureSqlDAO extends SqlConnection implements MonedaDAO {

    public MonedaAzureSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<MonedaEntity> consultar(final MonedaEntity entidad) {
        final List<MonedaEntity> listaMonedas = new ArrayList<>();
        final StringBuilder sentenciaSql = new StringBuilder();
        final List<Object> parametros = new ArrayList<>();

        sentenciaSql.append("SELECT Id, Nombre FROM Moneda WHERE 1 = 1");

        if (entidad.getId() != 0) {
            sentenciaSql.append(" AND Id = ?");
            parametros.add(entidad.getId());
        }

        if (!entidad.getNombre().isEmpty()) {
            sentenciaSql.append(" AND Nombre LIKE ?");
            parametros.add("%" + entidad.getNombre() + "%");
        }

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())) {
            int index = 1;
            for (Object parametro : parametros) {
                sentenciaPreparada.setObject(index++, parametro);
            }

            try (final ResultSet resultado = sentenciaPreparada.executeQuery()) {
                while (resultado.next()) {
                    listaMonedas.add(new MonedaEntity(resultado.getInt("Id"), resultado.getString("Nombre")));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "No ha sido posible consultar la información de los deportes. Por favor, inténtelo de nuevo o comuníquese con el administrador de la Unidad Deportiva El Bernabeu...";
            var mensajeTecnico ="Se ha presentado un problema ejecutando la sentancia SQL de consulta de las unidades deportivas en la base de datos Azure SQL";
            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = "No ha sido posible llevar a cabo la consulta de la informacion de los deportes. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu...";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO ejecutando la sentancia SQL de consulta de las unidades deportivas en la base de datos Azure SQL";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaMonedas;
    }
}

