package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;

public final class DeportePostgreSqlDAO extends SqlConnection implements DeporteDAO {

    public DeportePostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public final void crear(final DeporteEntity entidad) {
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO Deporte(Nombre) ");
        sentenciaSql.append("VALUES(?)");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){
            sentenciaPreparada.setString(1, entidad.getNombre());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception){
            var mensajeUsuario = "No ha sido posible llevar a cabo el registro de la información del nuevo pais. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Tienda Chepito...";
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023, entidad.getNombre());

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception){
            var mensajeUsuario = "No ha sido posible llevar a cabo el registro de la informacion del nuevo pais. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Tienda Chepito...";
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024, entidad.getNombre());

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public final void actualizar(final DeporteEntity entidad) {
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE Deporte");
        sentenciaSql.append("SET Nombre = ?");
        sentenciaSql.append("WHERE id = ?");

    }

    @Override
    public final void eliminar(final int id) {
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM Deporte");
        sentenciaSql.append("WHERE id = 1");
    }

    @Override
    public final List<DeporteEntity> consultar(DeporteEntity entidad) {

        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("SELECT id, nombre");
        sentenciaSql.append("FROM Deporte");
        sentenciaSql.append("ORDER BY nombre ASC");

        return null;
    }

}