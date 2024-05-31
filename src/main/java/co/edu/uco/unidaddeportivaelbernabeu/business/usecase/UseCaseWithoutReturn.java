package co.edu.uco.unidaddeportivaelbernabeu.business.usecase;

public interface UseCaseWithoutReturn<T> {
    void ejecutar(T data);
}
