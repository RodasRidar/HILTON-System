package arreglos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Boleta;

public class ArregloBoleta {
	//Atributo privado
		private ArrayList<Boleta> boleta;
		//Constructor
	public ArregloBoleta(){
		boleta=new ArrayList<Boleta>();
		cargarBoleta();
	}
		//Operaciones Publicas Basicas
	public void  adicionar(Boleta x){
		boleta.add(x);
		grabarBoleta();
	}
	public int  tamaño(){
		return boleta.size();
		}
	public Boleta obtener(int i){
		return boleta.get(i);
	}
	public Boleta buscar(int codigoBoleta){
		for(int i=0;i<tamaño();i++)
			if(obtener(i).getCodigoBoleta()==codigoBoleta)
				return obtener(i);
		return null;
	}
	public void eliminar(Boleta x){
		boleta.remove(x);
		grabarBoleta();;
	}

	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 10001;
		else
			return obtener(tamaño()-1).getCodigoBoleta() + 1;		
	}


	private void grabarBoleta() {
		try {
			PrintWriter pw;
			String linea;
			Boleta x;
			pw = new PrintWriter(new FileWriter("boleta.txt"));
			for (int i = 0; i < tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoBoleta() + ";" + x.getCodigoIngreso() + ";" + x.getPagoTotal();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}

	public void actualizarDatos() {
		grabarBoleta();;
		}

	private void cargarBoleta() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codigoBoleta;
			int codigoIngreso;
			double pagoTotal;
			br = new BufferedReader(new FileReader("bolta.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoBoleta = Integer.parseInt(s[0].trim());
				codigoIngreso = Integer.parseInt(s[1].trim());
				pagoTotal = Double.parseDouble(s[2].trim());
				adicionar(new Boleta(codigoBoleta, codigoIngreso, pagoTotal));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}

	}



