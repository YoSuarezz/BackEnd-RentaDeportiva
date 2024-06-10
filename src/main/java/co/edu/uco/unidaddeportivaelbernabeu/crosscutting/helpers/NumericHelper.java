package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers;

public final class NumericHelper {
	
	public static final int ZERO = 0;
	
	private NumericHelper() {
		super();
	}

	public static boolean isNullOrEmpty(final String value) {
		return value == null;
	}
	public static final int convertToInt(final String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception exception) {
			return ZERO;
		}
	}


}
