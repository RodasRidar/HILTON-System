package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;


import arreglos.*;
import clases.*;
import libreria.Fecha;
import libreria.Lib;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class JdHospedajes extends JDialog implements ActionListener {
	private JLabel lblHospedajes;
	private JComboBox<String> cboCodigoHospedaje;
	private JButton btnPagar;
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdHospedajes dialog = new JdHospedajes();
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
	public JdHospedajes() {
		setBounds(100, 100, 734, 439);
		setTitle("Pago||Hospedajes");
		getContentPane().setLayout(null);
		
		lblHospedajes = new JLabel("Hospedajes");
		lblHospedajes.setBounds(12, 13, 81, 16);
		getContentPane().add(lblHospedajes);
		
		cboCodigoHospedaje = new JComboBox<String>();
		cboCodigoHospedaje.addActionListener(this);
		cboCodigoHospedaje.setBounds(105, 13, 125, 22);
		getContentPane().add(cboCodigoHospedaje);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(this);
		btnPagar.setBounds(590, 9, 97, 25);
		getContentPane().add(btnPagar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 53, 671, 311);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		colocarCodigosHospedaje();

	}
//  Declaración global
	ArregloSocio as=new ArregloSocio();
	ArregloIngresos ai=new ArregloIngresos();
	ArregloHospedaje ah = new ArregloHospedaje();
	String fechaSalida, horaSalida;
	double costoHospedaje;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPagar) {
			actionPerformedBtnPagar(e);
		}
		if (e.getSource() == cboCodigoHospedaje) {
			actionPerformedCboCodigoHospedajes(e);
		}
	}
	protected void actionPerformedCboCodigoHospedajes(ActionEvent e) {
		//txtS.setText("");
		if (cboCodigoHospedaje.getSelectedIndex() >= 0)
			listar();

	}
	protected void actionPerformedBtnPagar(ActionEvent e) {
		if (cboCodigoHospedaje.getSelectedIndex() >= 0) { 
			int ok = confirmar("¿ Desea pagar Hospedaje ?");
			if (ok == 0) {
				Hospedajes h = ah.buscar(leerCodigoHospedaje());
				h.setEstado(1);
				h.setfechaSalida(fechaSalida);
				h.sethoraSalida(horaSalida);
				h.setcostoHospedaje(costoHospedaje);
				ah.actualizarDatos();
				ArregloBungalow ab = new ArregloBungalow();
				Bungalow b = ab.buscar(h.getnumeroBungalow());
				b.setEstado(0);
				ab.actualizarDatos();;
				cboCodigoHospedaje.removeItem(cboCodigoHospedaje.getSelectedItem());
			}
		}
		else
			mensaje("No existen Hospedajes pendientes de pago");
	}
//  Métodos tipo void (sin parámetros)
	void colocarCodigosHospedaje() {
		Hospedajes h;
		for (int j=0; j<ah.tamaño(); j++) {
			h = ah.obtener(j);
			if (h.getEstado() == 0)
				cboCodigoHospedaje.addItem("" + h.getCodigoHospedaje());
		}	
	}
	void imprimir() {
		imprimir("");
	}
	void listar() {
		Hospedajes h=ah.buscar(leerCodigoHospedaje());
		Ingresos i=ai.buscar(h.getCodigoIngreso());
		Socio s=as.buscar(i.getcodigoSocio());
		imprimir("Socio  :  " + s.getCodigoSocio());
		imprimir("Nombres   :  " + s.getNombres());
		imprimir("Apellidos :  " + s.getApellidos());
		imprimir();
		imprimir("Bungalow      :  " + h.getnumeroBungalow());
		ArregloBungalow ab = new ArregloBungalow();
		Bungalow b = ab.buscar(h.getnumeroBungalow());
		imprimir("Categoría :  " + Lib.CategoriaBungalow[b.getCategoria()]);
		imprimir();
		imprimir("Ingreso   :  " + i.getfechaIngreso()
		                         + " - " + i.gethoraIngreso());
		fechaSalida = Fecha.fechaActual();
		horaSalida = Fecha.horaActual();
		imprimir("Salida    :  " + fechaSalida
                                 + " - " + horaSalida);
		int dias = Fecha.diasTranscurridos(i.getfechaIngreso(), fechaSalida);
		costoHospedaje =h.getcostoHospedaje() + dias * b.getPrecioPorDia();
		imprimir();
		imprimir("1) COSTO DE HOSPEDAJE S/ " + formato(h.getCodigoHospedaje()));
		imprimir();
		imprimir("2) PRECIO POR DÍA S/ " + formato(b.getPrecioPorDia()));
		imprimir();
		imprimir("3) DÍAS TRANSCURRIDOS " + dias);
		imprimir();
		imprimir("4) TOTAL A PAGAR S/ " + formato(costoHospedaje));
	}
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	//  Métodos que retornan valor (sin parámetros)
	int leerCodigoHospedaje() {
		return Integer.parseInt(cboCodigoHospedaje.getSelectedItem().toString());
	}
	//  Métodos que retornan valor (con parámetros)
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
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
	
}


