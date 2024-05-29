package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data;

import static co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper.UNDERLINE;
import static co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper.concatenate;

public enum CodigoMensaje {

	M00001(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00001", true),
	M00002(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00002", true),
	M00003(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00003", true),
	M00004(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00004", true),
	M00005(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00005", true),
	M00006(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00006", true),
	M00007(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00007", true),
	M00008(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00008", true),
	M00009(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00009", true),
	M00010(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00010", true),
	M00011(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00011", true),
	M00012(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00012", true),
	M00013(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00013", true),
	M00014(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00014", true),
	M00015(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00015", true),
	M00016(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00016", true),
	M00017(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00017", true),
	M00018(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00018", true),
	M00019(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00019", true),
	M00020(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00020", true),
	M00021(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00021", true),
	M00022(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00022", true),
	M00023(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00023", false),
	M00024(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00024", false),
	M00025(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00025", false),
	M00026(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00026", false),
	M00027(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00027", false),
	M00028(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00028", false),
	M00029(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00029", false),
	M00030(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00030", true),
	M00031(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00031", false),
	M00032(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00032", true),
	M00033(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00033", true),
	M00034(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00034", true),
	M00035(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00035", false),
	M00036(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00036", false),
	M00037(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00037", false),
	M00038(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00038", false),
	M00039(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00039", false),
	M00040(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00040", true),
	M00041(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00041", true),
	M00042(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00042", true),
	M00043(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00043", true),
	M00044(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00044", true),
	M00045(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00045", true),
	M00046(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00046", true),
	M00047(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00047", true),
	M00048(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00048", true),
	M00049(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00049", true),
	M00050(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00050", true),
	M00051(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00051", true),
	M00052(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00052", true),
	M00053(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00053", true),
	M00054(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00054", true),
	M00055(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00055", true);

	private TipoMensaje tipo;
	private CategoriaMensaje categoria;
	private String codigo;
	private boolean base;

	private CodigoMensaje(final TipoMensaje tipo, final CategoriaMensaje categoria, final String codigo,
			final boolean base) {
		setTipo(tipo);
		setCategoria(categoria);
		setCodigo(codigo);
		setBase(base);
	}

	public final TipoMensaje getTipo() {
		return tipo;
	}

	public final CategoriaMensaje getCategoria() {
		return categoria;
	}

	public final String getCodigo() {
		return codigo;
	}

	public final boolean isBase() {
		return base;
	}

	private final void setTipo(final TipoMensaje tipo) {
		this.tipo = tipo;
	}

	private final void setCategoria(final CategoriaMensaje categoria) {
		this.categoria = categoria;
	}

	private final void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	private final void setBase(final boolean base) {
		this.base = base;
	}

	public final String getIdentificador() {
		return concatenate(getTipo().name(), UNDERLINE, getCategoria().name(), UNDERLINE, getCodigo());
	}
}
