package arreglos;

import java.io.*;
import java.util.ArrayList;

import clases.Ingresos;


public class ArregloIngresos {
	private ArrayList<Ingresos>ingreso;
	//Constructor
	public ArregloIngresos(){
		ingreso=new ArrayList<Ingresos>();
		cargarIngreso();
	}
	//Operaciones complementarias
	public void adicionar(Ingresos x){
		ingreso.add(x);
		grabarIngreso();
	}
	public int tamaño(){
		return ingreso.size();
	}
	public Ingresos obtener(int i){
		return ingreso.get(i);
	}
	public Ingresos buscar(int codigoingreso){
		for(int i=0;i<tamaño();i++)
			if(obtener(i).getcodigoIngreso()==codigoingreso)
				return obtener(i);
		return null;
	}
	public void eliminar(Ingresos x){
		ingreso.remove(x);
		grabarIngreso();
	}
	
	//Opeciones Complementarias
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 40001;
		else
			return obtener(tamaño()-1).getcodigoIngreso()	+1;
	}
	public boolean procedeCodigoSocio(int codigoSocio) {
		for (int i=tamaño()-1; i>=0; i--)
			if (obtener(i).getcodigoSocio() == codigoSocio  &&  obtener(i).getEstado() == 0)
				return false;
		return true;
	}
public void grabarIngreso() {
		try {
			PrintWriter pw;
			String linea;
			Ingresos x;
			pw = new PrintWriter(new FileWriter("ingreso.txt"));
			for (int i = 0; i < tamaño(); i++) {
				x = obtener(i);
				linea = x.getcodigoIngreso() + ";" + x.getcodigoSocio() + ";" + x.getfechaIngreso() + ";" + x.gethoraIngreso() + ";" + x.getnumeroInvitados()+";"+x.getcostoIngreso()+";"+x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}

	public void actualizarDatos() {
		grabarIngreso();
		}

	private void cargarIngreso() {
		try {
			BufferedReader br;
			String linea, fechaIngreso,horaIngreso;
			double costoIngreso;
			String[] s;
			int codigoIngreso,codigoSocio,numeroInvitados,estado;
			br = new BufferedReader(new FileReader("ingreso.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoIngreso = Integer.parseInt(s[0].trim());
				codigoSocio = Integer.parseInt(s[1].trim());
				fechaIngreso = s[2].trim();
				horaIngreso = s[3].trim();
				numeroInvitados = Integer.parseInt(s[4].trim());
				costoIngreso=Double.parseDouble(s[5].trim());
				estado = Integer.parseInt(s[6].trim());
				adicionar(new Ingresos(codigoIngreso, codigoSocio, fechaIngreso, horaIngreso, numeroInvitados, costoIngreso, estado));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	
	public boolean procedeCodigoIngreso(int codigoIngreso) {
		for (int i=tamaño()-1; i>=0; i--)
			if (obtener(i).getcodigoIngreso() == codigoIngreso  &&  obtener(i).getEstado() == 0)
				return false;
		return true;
	}
}
