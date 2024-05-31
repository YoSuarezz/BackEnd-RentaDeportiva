package co.edu.uco.unidaddeportivaelbernabeu.business.fachade;

public interface FacadeWithReturn<T,K> {
    K ejecutar(T dto);
}
