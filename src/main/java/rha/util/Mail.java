package rha.util;

public class Mail {
	private String de;
    private String para;
    private String asunto;
    private String contenido;

    public Mail() {
    }

	public Mail(String de, String para, String asunto, String contenido) {
		super();
		this.de = de;
		this.para = para;
		this.asunto = asunto;
		this.contenido = contenido;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
    
    

}
