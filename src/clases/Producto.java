package clases;

public class Producto {
//	Atributos privados
	private int codigoProducto,stock;
	private double preciounitario;
	private String detalle;
	//	Constructor
	public Producto(int codigoProducto,String detalle,double preciounitario,int stock){
		this.codigoProducto=codigoProducto;
		this.detalle=detalle;
		this.preciounitario=preciounitario;
		this.stock=stock;
	}
	//  Métodos de acceso público: set/get
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public void setPrecioUnitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
//GET//
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public String getDetalle() {
		return detalle;
	}
	public double getPrecioUnitario() {
		return preciounitario;
	}
	public int getStock() {
		return stock;
	}
	
}
