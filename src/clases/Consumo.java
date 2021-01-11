package clases;
public class Consumo {
	//Atributos privados
	private int codigoProducto;
	private int cantidad;
	private double precioUitario;
	//Constructor
	public Consumo(int codigoProducto , int cantidad , double precioUnitario ){
		this.codigoProducto=codigoProducto;
		this.cantidad=cantidad;
		this.precioUitario=precioUnitario;
	}
	//Métodos publicos set/get
	public void setCodigoProducto(int codigoProducto){
		this.codigoProducto=codigoProducto;
	}
	public void setCantidad(int cantidad){
		this.cantidad=cantidad;
	}
	public void setPrecioUnitario(double precioUnitario){
		this.precioUitario=precioUnitario;
	}
	public int getCodigoProducto(){
		return codigoProducto;
	}
	public int getCantidad(){
		return cantidad;
	}
	public double getPrecioUnitario(){
		return precioUitario;
	}

}
