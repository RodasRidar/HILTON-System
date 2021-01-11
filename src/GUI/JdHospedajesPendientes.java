package GUI;

import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import arreglos.ArregloHospedaje;
import arreglos.ArregloIngresos;
import arreglos.ArregloSocio;
import clases.Hospedajes;
import clases.Ingresos;
import clases.Socio;

public class JdHospedajesPendientes extends JDialog {
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdHospedajesPendientes dialog = new JdHospedajesPendientes();
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
	public JdHospedajesPendientes() {
		setBounds(100, 100, 776, 475);
		setTitle("Reporte||Hospedajes Pendientes");
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 734, 402);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		listar();
	}
//  Métodos tipo void (sin parámetros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloHospedaje ah=new  ArregloHospedaje();
		ArregloIngresos ai=new ArregloIngresos();
		ArregloSocio  as =new  ArregloSocio();
		Hospedajes h;
		Ingresos i;
		Socio  s;
		txtS.setText("");
		for(int j=0;j<ah.tamaño();j++){
			h=ah.obtener(j);
			if(h.getEstado()==0){
				i=ai.buscar(h.getCodigoIngreso());
				s=as.buscar(i.getcodigoSocio());
				imprimir("Código de Hospedaje :  " + h.getCodigoHospedaje());
				imprimir("Código de Socio      :  " + s.getCodigoSocio());
				imprimir("Nombres                 :  " + s.getNombres());
				imprimir("Apellidos               :  " + s.getApellidos());
				imprimir("Fecha de ingreso        :  " + i.getfechaIngreso()
				                                       + " - " + i.gethoraIngreso());
				imprimir("Costo Total del  hospedaje :"+h.getcostoHospedaje());
				imprimir();
			}
		}
	
	
	
	
	}
	
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	//  Métodos que retornan valor (con parámetros)
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	
}

