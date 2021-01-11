package GUI;

import clases.*;
import arreglos.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JdIngresosyConsumosPendientes extends JDialog {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdIngresosyConsumosPendientes dialog = new JdIngresosyConsumosPendientes();
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
	public JdIngresosyConsumosPendientes() {
		setResizable(false);
		setTitle("Reporte | Ingresos y Consumos Pendientes");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 675, 345);
		getContentPane().add(scrollPane);
	
		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		listar();
	}
	
	//  Métodos tipo void (sin parámetros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloIngresos ai = new ArregloIngresos();
		ArregloSocio as = new ArregloSocio();
		Ingresos i;
		Socio s;
		txtS.setText("");
		for (int j=0; j<ai.tamaño(); j++) {
			i = ai.obtener(j);
			if (i.getEstado() == 0) {
				s = as.buscar(i.getcodigoSocio());
				imprimir("Código de Ingreso :  " + i.getcodigoIngreso());
				imprimir("Código de Socio     :  " + i.getcodigoSocio());
				imprimir("Nombres                 :  " + s.getNombres());
				imprimir("Apellidos               :  " + s.getApellidos());
				imprimir("Fecha de ingreso        :  " + i.getfechaIngreso()
				                                       + " - " + i.gethoraIngreso());
				imprimir();
				ArregloConsumo ac=new ArregloConsumo(""+i.getcodigoIngreso());
				Consumo c;
				imprimir("               Cantidad Precio Importe");
				double  pago;
				for(int a=0;a<ac.tamaño();a++){
					c=ac.obtener(a);
					pago=c.getCantidad()*c.getPrecioUnitario();
					ArregloProducto ap=new ArregloProducto();
					Producto p=ap.buscar(c.getCodigoProducto());
					imprimir("  "+formato(p.getDetalle())+formato(c.getCantidad())+formato(c.getPrecioUnitario())+formato(pago));
				imprimir("TOTAL  A PAGAR S/ "+formato(pago));
				}
				imprimir();
				imprimir();
				
				
			}
		}
	}
	
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	String formato(String cadena) {
		return String.format("%-15s", cadena);
	}
	String formato(int entero) {
		return String.format("%-10d", entero);
	}
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
}