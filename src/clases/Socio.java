package clases;

public class Socio {
//	Atributos privados
	private int codigoSocio,telefono;
	private String nombres, apellidos, dni;
	//	Constructor
	public Socio(int codigoSocio, String nombres, String apellidos, String dni,int telefono) {
		this.codigoSocio = codigoSocio;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
	}
	//  Métodos de acceso público: set/get
	public void setCodigoPaciente(int codigoSocio) {
		this.codigoSocio = codigoSocio;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getCodigoSocio() {
		return codigoSocio;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public int getTelefono() {
		return telefono;
	}
	public String getDni() {
		return dni;
	}
	
}

