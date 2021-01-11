package arreglos;
import java.io.*;
import java.util.ArrayList;

import clases.Producto;

public class ArregloProducto {
private ArrayList <Producto> pr;
	
	public ArregloProducto() {
		pr = new ArrayList <Producto> ();
		
		cargarProducto();
	}
	
	public void adicionar(Producto x){
		pr.add(x);
		grabarProducto();
	}
	
	public int tamaño(){
		return pr.size();
	}
	
	public Producto obtener(int i){
		return pr.get(i);
	}
	
	
	public Producto buscar(int codigoproducto){
		for(int i=0;i<tamaño();i++)
			if(obtener(i).getCodigoProducto()==codigoproducto)
				return obtener(i);
		return null;
	}
	
		public void eliminar(Producto x) {
			pr.remove(x);
			grabarProducto();
		}
	//  Operaciones públicas complementarias
		public int codigoCorrelativo() {
			if (tamaño() == 0)
				return 20001;
			else
				return obtener(tamaño()-1).getCodigoProducto()+1;
		}
public void actualizarDatos(){
	grabarProducto();
}
		private void grabarProducto() {
			try {
				PrintWriter pw;
				String linea;
				Producto x;
				pw = new PrintWriter(new FileWriter("producto.txt"));
				for (int i = 0; i < tamaño(); i++) {
					x = obtener(i);
					linea = x.getCodigoProducto() + ";" + x.getDetalle() + ";" + x.getPrecioUnitario() + ";" + x.getStock();
					pw.println(linea);
				}
				pw.close();
			}
			catch (Exception e) {
			}
		}
		
		private void cargarProducto() {
			try {
				BufferedReader br;
				String linea, detalle;
				String[] s;
				int codigoProducto,stock;
				double preciounitario;
				br = new BufferedReader(new FileReader("producto.txt"));
				while ((linea = br.readLine()) != null) {
					s = linea.split(";");
					codigoProducto = Integer.parseInt(s[0].trim());
					detalle = s[1].trim();
					preciounitario = Double.parseDouble(s[2].trim());
					stock = Integer.parseInt(s[3].trim());
					adicionar(new Producto(codigoProducto, detalle, preciounitario, stock));
				}
				br.close();
			}
			catch (Exception e) {
			}
		}

}


