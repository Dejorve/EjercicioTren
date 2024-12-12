package tren;

public class Tren {
	private int asientos;
	private double duracion, kmRecorrido;
	private String tipo, referencia;

	public Tren() { // constructor por defecto
		this.tipo = "";
		this.duracion = 0;
		this.kmRecorrido = 0;
		this.asientos = 0;
		this.referencia = "";

	}

	public Tren(String t, String r, double d, double k, int a) { // constructor
		this.tipo = t;
		this.referencia = r;
		this.duracion = d;
		this.kmRecorrido = k;
		this.asientos = a;
		

	}

	public int getAsientos() {
		return asientos;
	}

	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public double getKmRecorrido() {
		return kmRecorrido;
	}

	public void setKmRecorrido(double kmRecorrido) {
		this.kmRecorrido = kmRecorrido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tren [asientos=" + asientos + ", referencia=" + referencia + ", duracion=" + duracion + ", kmRecorrido="
				+ kmRecorrido + ", tipo=" + tipo + "]";
	}

	public boolean trenLleno() {
		boolean lleno = false;
		if (this.asientos >= 400) {
			lleno = true;
		}
		return lleno;

	}

	public double obtenerVelocidadTotal(double duracion, double kmRecorrido) {


		return this.kmRecorrido / (this.duracion/60);

	}

}
