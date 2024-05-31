package co.edu.uco.unidaddeportivaelbernabeu.business.usecase;

public interface UseCaseWithReturn<T,R> {
    R ejecutar(T data);
}
