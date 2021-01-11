package clases;

public class Ingresos {
	private int codigoIngreso,codigoSocio,numeroInvitados,estado;
	private String fechaIngreso,horaIngreso;
	private double costoIngreso;
	//


//CONSTRUCTOR  INGRESOS
public Ingresos(int codigoIngreso,int codigoSocio,String fechaIngreso,String horaIngreso,int numeroInvitados,double costoIngreso,int estado){
	this.codigoIngreso=codigoIngreso;
	this.codigoSocio=codigoSocio;
	this.fechaIngreso=fechaIngreso;
	this.horaIngreso=horaIngreso;
	this.numeroInvitados=numeroInvitados;
	this.costoIngreso=costoIngreso;
	this.estado=estado;
		}

//METODOS  SET/GET
	public void setcodigoIngreso(int codigoIngreso){
	this.codigoIngreso=codigoIngreso;
	}
	public void setcodigoSocio(int codigoSocio){
		this.codigoSocio=codigoSocio;
		}
	public void setfechaIngreso(String fechaIngreso){
		this.fechaIngreso=fechaIngreso;
		}
	public void sethoraIngreso(String horaIngreso){
		this.horaIngreso=horaIngreso;
		}
	public void setnumeroInvitados(int numeroInvitados){
		this.numeroInvitados=numeroInvitados;
		}
	public void setcostoIngreso(double costoIngreso){
		this.costoIngreso=costoIngreso;
		}
	public void setEstado(int estado){
		this.estado=estado;
	}
	public int getcodigoIngreso(){
		return codigoIngreso;
	}
	public int getcodigoSocio(){
		return codigoSocio;
	}
	public String getfechaIngreso(){
		return fechaIngreso;
	}
	public String gethoraIngreso(){
		return horaIngreso;
	}
	public int getnumeroInvitados(){
		return numeroInvitados;
	}
	public double getcostoIngreso(){
		return costoIngreso;
	}
	public int getEstado(){
		return estado;
	}
}
