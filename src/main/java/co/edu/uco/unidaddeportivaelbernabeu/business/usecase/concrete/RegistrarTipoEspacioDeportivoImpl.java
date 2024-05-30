package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.RegistrarTipoEspacioDeportivo;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

public class  RegistrarTipoEspacioDeportivoImpl implements RegistrarTipoEspacioDeportivo {

    private final DAOFactory factory;

    public RegistrarTipoEspacioDeportivoImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        //POL01
        //validarNoExistenciaTipoEspacioDeportivo(tipoEspacioDeportivo.getNombre(),tipoEspacioDeportivo.getDeporte().getId());
        //2. guardar la informacion de la nuevo tipo de espacio deportivo
        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivo);
        factory.getTipoEspacioDeportivoDAO().crear(tipoEspacioDeportivoEntity);
    }

    /*private void validarNoExistenciaTipoEspacioDeportivo(String nombreTipoEspacioDeportivo, int IdDeporte) {
        var deporteEntity = DeporteEntity.build(IdDeporte);
        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntity.build(0, nombreTipoEspacioDeportivo, deporteEntity);

        final List<TipoEspacioDeportivoEntity> resultados = factory.getTipoEspacioDeportivoDAO().consultar(tipoEspacioDeportivoEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = "Ya existe un tipo de espacio deportivo en con nombre {1} asociada al deporte deseado";
            throw new BusinessUDElBernabeuException(mensajeUsuario);

     */
}