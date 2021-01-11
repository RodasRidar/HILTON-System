package arreglos;

import clases.Consumo;

import java.io.*;
import java.util.ArrayList;

public class ArregloConsumo {
	
	//  Atributos privados
	private ArrayList <Consumo> consumo;
	private String codigoConsumo;
	//  Constructor
    	public ArregloConsumo(String codigoConsumo) {
    		this.codigoConsumo=codigoConsumo;
    		consumo = new ArrayList<Consumo>();
    		cargarConsumo();
    }   
	//  Operaciones publicas basicas
	public void adicionar(Consumo x) {
		consumo.add(x);
		grabarConsumo();
	}
    public int tamaño() {
		return consumo.size();
	}
	public Consumo obtener(int i) {
		return consumo.get(i);
	}
	public Consumo buscar(int codigoProducto) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoProducto() == codigoProducto)
				return obtener(i);
		return null;
	}
	public void eliminar(Consumo x) {
		consumo.remove(x);
		grabarConsumo();
	}
	public void grabarConsumo() {
		try {
			PrintWriter pw;
			String linea;
			Consumo x;
			pw = new PrintWriter(new FileWriter(codigoConsumo + ".txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea =x.getCodigoProducto()+ ";" +
						x.getCantidad() + ";" +
						x.getPrecioUnitario();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarConsumo() {
		try {
			BufferedReader br;
			String linea; 
			String[] s;
			int codigoProducto, cantidad;
			double precioUnitario;
			br = new BufferedReader(new FileReader(codigoConsumo + ".txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoProducto = Integer.parseInt(s[0].trim());
				cantidad = Integer.parseInt(s[1].trim());
				precioUnitario = Double.parseDouble(s[2].trim());
				adicionar(new Consumo(codigoProducto, cantidad, precioUnitario));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	
	
}