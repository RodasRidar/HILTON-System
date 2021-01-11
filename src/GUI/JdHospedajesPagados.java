package GUI;

import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import arreglos.ArregloBungalow;
import arreglos.ArregloHospedaje;
import arreglos.ArregloIngresos;
import arreglos.ArregloSocio;
import clases.Bungalow;
import clases.Hospedajes;
import clases.Ingresos;
import clases.Socio;
import libreria.Lib;

public class JdHospedajesPagados extends JDialog {
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdHospedajesPagados dialog = new JdHospedajesPagados();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public JdHospedajesPagados() {
		setBounds(100, 100, 738, 448);
		setTitle("Reporte||Hospedajes Pagados");
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 696, 375);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		listar();
	}
//  M�todos tipo void (sin par�metros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloHospedaje ah= new ArregloHospedaje();
		ArregloSocio as= new ArregloSocio();
		ArregloBungalow ab = new ArregloBungalow();
		ArregloIngresos ai=new ArregloIngresos();
		Hospedajes h;
		Socio s;
		Bungalow b;
		Ingresos i;
		txtS.setText("");
		for (int j=0; j<ah.tama�o(); j++) {
			h= ah.obtener(j);
			if (h.getEstado() == 1) {
				i=ai.buscar(h.getCodigoIngreso());
				s = as.buscar(i.getcodigoSocio());
				b = ab.buscar(h.getnumeroBungalow());
				imprimir("--------------------------------------");
				imprimir("C�digo de Hospedaje :  " + h.getCodigoHospedaje());
				imprimir("C�digo de Socio     :  " + s.getCodigoSocio());
				imprimir("Nombres                 :  " + s.getNombres());
				imprimir("Apellidos               :  " + s.getApellidos());
				imprimir("N�mero de bungalow        :  " + h.getnumeroBungalow());
				imprimir("Categor�a               :  " + Lib.CategoriaBungalow[b.getCategoria()]);
				imprimir("Precio por d�a          :  S/ " + formato(b.getPrecioPorDia()));
				imprimir("Fecha de ingreso        :  " + i.getfechaIngreso()
				                                       + " - " + i.gethoraIngreso());
				imprimir("----------------------------------------");
				
			imprimir("Costo  total :"+h.getcostoHospedaje());
			}
		}
	}
	//  M�todos tipo void (con par�metros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	//  M�todos que retornan valor (con par�metros)
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	
}

