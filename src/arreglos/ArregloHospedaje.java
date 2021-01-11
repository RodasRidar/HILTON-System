package arreglos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Hospedajes;

     public class ArregloHospedaje {
      ArrayList<Hospedajes> hos; 
	  public ArregloHospedaje(){
	     hos = new ArrayList<Hospedajes>();
	     cargarHospedaje();
	  
	  }	 
	  public void adicionar(Hospedajes x) {
			hos.add(x);
			grabarHospedaje();
		}
		public int tamaño() {
			return hos.size();
		}
		public Hospedajes obtener(int i) {
			return hos.get(i);
		}
		public Hospedajes buscar(int codigoHospedaje) {
			for (int i=0; i<tamaño(); i++)
				if (obtener(i).getCodigoHospedaje() == codigoHospedaje)
					return obtener(i);
			return null;
		}
		public void eliminar(Hospedajes x) {
			hos.remove(x);
			grabarHospedaje();
		}
		public int codigoCorrelativo() {
			if (tamaño() == 0)
				return 50001;
			else
				return obtener(tamaño()-1).getCodigoHospedaje()	+1;
		}
		public boolean procedeCodigoIngreso(int codigoIngreso) {
			for (int i=tamaño()-1; i>=0; i--)
				if (obtener(i).getCodigoIngreso() == codigoIngreso)
					if (obtener(i).getEstado() == 0)
						return false;
					else
						return true;
			return true;
		}
		
		
		
		public void grabarHospedaje() {
			try {
				PrintWriter pw;
				String linea;
				Hospedajes x;
				pw = new PrintWriter(new FileWriter("hospedaje.txt"));
				for (int i = 0; i < tamaño(); i++) {
					x = obtener(i);
					linea = x.getCodigoHospedaje()+ ";" + x.getCodigoIngreso() + ";" + x.getnumeroBungalow()+ ";" + x.getfechaSalida() + ";" + x.getfechaSalida()+";"+x.getcostoHospedaje()+";"+x.getEstado();
					pw.println(linea);
				}
				pw.close();
			}
			catch (Exception e) {
			}
		}

		public void actualizarDatos() {
			grabarHospedaje();
			}

		private void cargarHospedaje() {
			try {
				BufferedReader br;
				String linea, fechaSalida,horaSalida;
				double costoHospedaje;
				String[] s;
				int codigoHospedaje,codigoIngreso,numeroBungalow,estado;
				br = new BufferedReader(new FileReader("hospedaje.txt"));
				while ((linea = br.readLine()) != null) {
					s = linea.split(";");
					codigoHospedaje = Integer.parseInt(s[0].trim());
					codigoIngreso = Integer.parseInt(s[1].trim());
					numeroBungalow = Integer.parseInt(s[2].trim());
					fechaSalida = s[3].trim();
					horaSalida = s[4].trim();
					costoHospedaje=Double.parseDouble(s[5].trim());
					estado = Integer.parseInt(s[6].trim());
					adicionar(new Hospedajes(codigoHospedaje, codigoIngreso, numeroBungalow, fechaSalida, horaSalida, costoHospedaje, estado));
				}
				br.close();
			}
			catch (Exception e) {
			}
		}
		
     }
		
					
		
		
	

	





	
		
			
		

	
	
	
	
	
	
	


