package clases;

public class Hospedajes {
  private int codigoHospedaje,codigoIngreso,numeroBungalow,estado;
  private String fechaSalida,horaSalida;
  private  double costoHospedaje;
  
  
  
//CONSTRUCTOR//
  public Hospedajes(int codigoHospedaje,int codigoIngreso,int numeroBungalow,String fechaSalida,String horaSalida,double costoHospedaje,int estado){
	  		this.codigoHospedaje=codigoHospedaje;
	  		this.codigoIngreso=codigoIngreso;
	  		this.numeroBungalow=numeroBungalow;
	  		this.fechaSalida=fechaSalida;
	  		this.horaSalida=horaSalida;
	  		this.costoHospedaje=costoHospedaje;
	  		this.estado=estado;
  		}
  ///Set//
  	public void setCodigoHospedaje(int codigoHospedaje){
  		this.codigoHospedaje=codigoHospedaje;
  	}
  	public void setCodigoIngreso(int codigoIngreso){
  		this.codigoIngreso=codigoIngreso;
  	}
  	public void setnumeroBungalow(int numeroBungalow){
  		this.numeroBungalow=numeroBungalow;
  	}
  	public void setfechaSalida(String fechasalida){
  		this.fechaSalida=fechasalida;
  	}
  	public void sethoraSalida(String horaSalida){
  		this.horaSalida=horaSalida;
  	}
  	public void setcostoHospedaje(double costoHospedaje){
  		this.costoHospedaje=costoHospedaje;
  	}
  	public void setEstado(int estado){
  		this.estado=estado;
  	}
 ///GET///
  	public int getCodigoHospedaje(){
  		return codigoHospedaje;
  	}
  	public int getCodigoIngreso(){
  		return codigoIngreso;
  	}
  	public int getnumeroBungalow(){
  		return numeroBungalow;
  	}
  	public String getfechaSalida(){
  		return fechaSalida;
  	}
  	public String gethoraSalida(){
  		return horaSalida;
  	}
  	public double getcostoHospedaje(){
  		return costoHospedaje;
  	}
  	public  int getEstado(){
  		return estado;
  	}
  
}
