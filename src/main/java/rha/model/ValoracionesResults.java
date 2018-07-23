package rha.model;

public class ValoracionesResults {
	private double notaMedia;
	private Sanitario sanitario;
	
	public ValoracionesResults(double notaMedia, Sanitario sanitario) {
        this.notaMedia = notaMedia;
        this.sanitario = sanitario;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public Sanitario getSanitario() {
        return sanitario;
    }

}
