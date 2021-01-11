package clases;

public class Boleta {
	private int codigoBoleta,codigoIngreso;
	private double pagoTotal;
	
	public Boleta(int codigoBoleta,int codigoIngreso,double pagoTotal){
		this.codigoBoleta=codigoBoleta;
		this.codigoIngreso=codigoIngreso;
		this.pagoTotal=pagoTotal;
	}
	
	//SET//
	public void  setCodigoBoleta(int codigoBoleta){
		this.codigoBoleta=codigoBoleta;
	}
	public void setCodigoIngreso(int codigoIngreso){
		this.codigoIngreso=codigoIngreso;
	}
	public void setPagoTotal(double pagoTotal){
		this.pagoTotal=pagoTotal;
	}
	
	//GET//
	public int getCodigoBoleta(){
		return codigoBoleta;
	}
	public int getCodigoIngreso(){
		return codigoIngreso;
	}
	public double getPagoTotal(){
		return pagoTotal;
	}
}
