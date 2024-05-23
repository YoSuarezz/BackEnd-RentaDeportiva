package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.enums.Lugar;

public final class InitializerUDElBernabeuException extends UnidadDeportivaElBernabeuException {

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.INITIALIZER;

	public InitializerUDElBernabeuException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}

	public InitializerUDElBernabeuException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}

	public InitializerUDElBernabeuException(final String mensajeTecnico, final String mensajeUsuario,
											final Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
}
