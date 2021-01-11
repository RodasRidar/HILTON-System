 package GUI;

import java.awt.EventQueue;
import java.util.Locale;
import clases.*;
import arreglos.*;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JdIngresosyConsumosPagados extends JDialog {
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdIngresosyConsumosPagados dialog = new JdIngresosyConsumosPagados();
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
	public JdIngresosyConsumosPagados() {
		setBounds(100, 100, 759, 456);
		setTitle("Reporte||Ingresos  y Consumos  Pagados");
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 717, 383);
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
		ArregloHospedaje ah=new  ArregloHospedaje();
		ArregloIngresos ai=new ArregloIngresos();
		ArregloSocio  as =new  ArregloSocio();
		Hospedajes h;
		Ingresos i;
		Socio  s;
		txtS.setText("");
		for(int j=0;j<ah.tama�o();j++){
			h=ah.obtener(j);
			if(h.getEstado()==1){
				i=ai.buscar(h.getCodigoIngreso());
				s=as.buscar(i.getcodigoSocio());
				imprimir("C�digo de Hospedaje :  " + h.getCodigoHospedaje());
				imprimir("C�digo de Socio      :  " + s.getCodigoSocio());
				imprimir("Nombres                 :  " + s.getNombres());
				imprimir("Apellidos               :  " + s.getApellidos());
				imprimir("Fecha de ingreso        :  " + i.getfechaIngreso()
				                                       + " - " + i.gethoraIngreso());
				imprimir("Costo Total del  hospedaje :"+h.getcostoHospedaje());
				imprimir();
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

