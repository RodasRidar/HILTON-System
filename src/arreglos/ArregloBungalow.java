package arreglos;

import java.io.*;
import java.util.ArrayList;

import clases.Bungalow;


public class ArregloBungalow {
private ArrayList<Bungalow> bun;
	
	public ArregloBungalow(){
		bun = new ArrayList<Bungalow>();
		cargarBungalow();
	}
	
	public void adicionar(Bungalow x){
		bun.add(x);
		grabarBungalow();
	}
	public int tamaño(){
		return bun.size();
	}
	public Bungalow obtener(int i){
		return bun.get(i);
	}
	public Bungalow buscar(int numerobungalow){
			for(int i=0;i<tamaño();i++)
				if(obtener(i).getNumeroBungalow()==numerobungalow)
					return obtener(i);
			return null;
		}
	
	public void eliminar(Bungalow x){
		bun.remove(x);
		grabarBungalow();
	}
	public int numeroCorrelativo(){
		if(tamaño()==0)
			return 30001;
		else
			return obtener(tamaño()-1).getNumeroBungalow()+1;
	}

private void grabarBungalow() {
	try {
		PrintWriter pw;
		String linea;
		Bungalow x;
		pw = new PrintWriter(new FileWriter("bungalow.txt"));
		for (int i = 0; i < tamaño(); i++) {
			x = obtener(i);
			linea = x.getNumeroBungalow() + ";" + x.getCategoria() + ";" + x.getPrecioPorDia() + ";" + x.getEstado();
			pw.println(linea);
		}
		pw.close();
	}
	catch (Exception e) {
	}
}
public void actualizarDatos() {
	grabarBungalow();
}
private void cargarBungalow() {
	try {
		BufferedReader br;
		String linea;
		String[] s;
		double precioPorDia;
		int numeroBungalow,categoria,estado;
		br = new BufferedReader(new FileReader("bungalow.txt"));
		while ((linea = br.readLine()) != null) {
			s = linea.split(";");
			numeroBungalow = Integer.parseInt(s[0].trim());
			categoria =Integer.parseInt(s[1].trim());
			precioPorDia = Double.parseDouble(s[2].trim());
			estado=Integer.parseInt(s[3].trim());
			adicionar(new Bungalow(numeroBungalow, categoria, precioPorDia, estado));
		}
		br.close();
	}
	catch (Exception e) {
	}
	

}
}


