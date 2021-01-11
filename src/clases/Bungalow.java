package clases;

public class Bungalow {

	//  Atributos privados
	private int  numeroBungalow,categoria,estado;
	private double precioPorDia;	
	//  Constructor
    public Bungalow(int numeroBungalow, int categoria, double precioPorDia,int estado) {
        this.numeroBungalow = numeroBungalow;
        this.categoria = categoria;
        this.precioPorDia = precioPorDia;
        this.estado=estado;
    }
    //  Métodos de acceso público: set/get
	public void setNumeroBungalow(int numeroBungalow) {
		this.numeroBungalow = numeroBungalow;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public void setPrecioPorDia(double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	//GET//
	public int getNumeroBungalow() {
		return numeroBungalow;
	}
	public int getCategoria() {
		return categoria;
	}
	public double getPrecioPorDia() {
		return precioPorDia;
	}
	public int getEstado() {
		return estado;
	}
}
	

