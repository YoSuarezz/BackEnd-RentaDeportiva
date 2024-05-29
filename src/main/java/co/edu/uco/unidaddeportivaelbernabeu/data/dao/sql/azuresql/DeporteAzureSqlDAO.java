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
import org.springframework.context.support.MessageSourceAccessor;

public final class DeporteAzureSqlDAO extends SqlConnection implements DeporteDAO {

    public DeporteAzureSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<DeporteEntity> consultar(final DeporteEntity entidad) {
        final List<DeporteEntity> listaDeportes = new ArrayList<>();
        final StringBuilder sentenciaSql = new StringBuilder();
        final List<Object> parametros = new ArrayList<>();

        sentenciaSql.append("SELECT Id, Nombre FROM Deporte WHERE 1 = 1");

        if (entidad.getId() != 0) {
            sentenciaSql.append(" AND Identificador = ?");
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
                    listaDeportes.add(new DeporteEntity(resultado.getInt("Id"), resultado.getString("Nombre")));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00039);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaDeportes;
    }
}