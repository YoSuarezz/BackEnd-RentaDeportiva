package co.edu.uco.unidaddeportivaelbernabeu.api.response;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class Response<T> {
    private List<String> mensajes = new ArrayList<>();
    private List<T> datos;

    public final List<String> getMensajes() {
        return mensajes;
    }

    public final void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public final List<T> getDatos() {
        return datos;
    }

    public final void setDatos(List<T> datos) {
        this.datos = ObjectHelper.getObjectHelper().getDefault(datos, new ArrayList<>());
    }

}
