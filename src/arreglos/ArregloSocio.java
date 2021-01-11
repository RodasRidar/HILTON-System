package arreglos;
import java.io.*;
import java.util.ArrayList;


import clases.Socio;

public class ArregloSocio {
	//Atributo privado
	private ArrayList<Socio> socio;
	//Constructor
public ArregloSocio(){
	socio=new ArrayList<Socio>();
	cargarSocio();
}
	//Operaciones Publicas Basicas
public void  adicionar(Socio x){
	socio.add(x);
	grabarSocio();
}
public int  tamaño(){
	return socio.size();
	}
public Socio obtener(int i){
	return socio.get(i);
}
public Socio buscar(int codigoSocio){
	for(int i=0;i<tamaño();i++)
		if(obtener(i).getCodigoSocio()==codigoSocio)
			return obtener(i);
	return null;
}
public void eliminar(Socio x){
	socio.remove(x);
	grabarSocio();
}

public int codigoCorrelativo() {
	if (tamaño() == 0)
		return 10001;
	else
		return obtener(tamaño()-1).getCodigoSocio() + 1;		
}


private void grabarSocio() {
	try {
		PrintWriter pw;
		String linea;
		Socio x;
		pw = new PrintWriter(new FileWriter("socio.txt"));
		for (int i = 0; i < tamaño(); i++) {
			x = obtener(i);
			linea = x.getCodigoSocio() + ";" + x.getNombres() + ";" + x.getApellidos() + ";" + x.getDni() + ";" + x.getTelefono();
			pw.println(linea);
		}
		pw.close();
	}
	catch (Exception e) {
	}
}

public void actualizarDatos() {
	grabarSocio();
	}

private void cargarSocio() {
	try {
		BufferedReader br;
		String linea, nombres,apellidos,dni;
		String[] s;
		int codigoSocio,telefono;
		br = new BufferedReader(new FileReader("socio.txt"));
		while ((linea = br.readLine()) != null) {
			s = linea.split(";");
			codigoSocio = Integer.parseInt(s[0].trim());
			nombres = s[1].trim();
			apellidos = s[2].trim();
			dni = (s[3].trim());
			telefono = Integer.parseInt(s[4].trim());
			adicionar(new Socio(codigoSocio, nombres, apellidos, dni, telefono));
		}
		br.close();
	}
	catch (Exception e) {
	}
}

}

