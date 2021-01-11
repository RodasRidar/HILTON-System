package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import arreglos.ArregloBoleta;
import arreglos.ArregloConsumo;
import arreglos.ArregloHospedaje;
import arreglos.ArregloIngresos;
import arreglos.ArregloProducto;
import arreglos.ArregloSocio;
import clases.Boleta;
import clases.Consumo;
import clases.Hospedajes;
import clases.Ingresos;
import clases.Producto;
import clases.Socio;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JdBoletas extends JDialog implements ActionListener {
	private JLabel lblCodigoIngreso;
	private JComboBox<String> cboCodigoIngresoP;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdBoletas dialog = new JdBoletas();
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
	public JdBoletas() {
		setBounds(100, 100, 614, 732);
		setTitle("Boleta||Boletas");
		getContentPane().setLayout(null);
		
		lblCodigoIngreso = new JLabel("INGRESO");
		lblCodigoIngreso.setBounds(12, 69, 119, 16);
		getContentPane().add(lblCodigoIngreso);
		
		cboCodigoIngresoP = new JComboBox<String>();
		cboCodigoIngresoP.setBounds(99, 66, 140, 22);
		getContentPane().add(cboCodigoIngresoP);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(454, 33, 97, 25);
		getContentPane().add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 101, 572, 571);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		lblBoleta = new JLabel("Boleta : ");
		lblBoleta.setBounds(12, 13, 56, 16);
		getContentPane().add(lblBoleta);
		
		txtBoleta = new JTextField();
		txtBoleta.setEditable(false);
		txtBoleta.setBounds(64, 10, 116, 22);
		getContentPane().add(txtBoleta);
		txtBoleta.setColumns(10);
		colocarCodigosingresos();
		
		txtBoleta.setText("60001");
	}
	ArregloIngresos ai=new ArregloIngresos();
	ArregloBoleta ab=new ArregloBoleta();
	ArregloSocio as=new ArregloSocio();
	ArregloHospedaje ah=new ArregloHospedaje();
	
	Ingresos in;
	Socio s;
	Boleta x;
	Hospedajes h;
	double pagoTotal;
	
	private JLabel lblBoleta;
	private JTextField txtBoleta;
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		int codigoBoleta =leerCodigoboleta();
		int codigoIngreso=leerCodigo();
		 Boleta nuevo = new Boleta(codigoBoleta, codigoIngreso, pagoTotal);
			ab.adicionar(nuevo);
			listar();
			
	}
	
	void listar(){
		double pagoTotal;
		x=ab.buscar(leerCodigoboleta());
		in = ai.buscar(leerCodigo());
		s=as.buscar(in.getcodigoSocio());
		txtS.setText("");
		imprimirEncabezado();
		imprimir("             CODIGO BOLETA : "+leerCodigoboleta() );
		imprimir("CODIGO SOCIO : "+in.getcodigoSocio());
		imprimir("NOMBRE  DEL SOCIO : "+s.getNombres());
		imprimir("APELLIDOS DEL SOCIO : "+ s.getApellidos());
		imprimir("DNI DEL  SOCIO : "+s.getDni());
		imprimir("------------------------------------------------");
		imprimir("CODIGO INGRESO : "+in.getcodigoIngreso());
		imprimir("FECHA DE  INGRESO : "+in.getfechaIngreso());
		imprimir("HORA DE INGRESO : "+in.gethoraIngreso());
		imprimir("NUMERO  DE INVITADOS : "+in.getnumeroInvitados());
		imprimir("-------------------------------------------------");
		ArregloConsumo ac=new ArregloConsumo(""+leerCodigo());
		Consumo c;
		double  pago = 0;
		for(int i=0;i<ac.tamaño();i++){
			c=ac.obtener(i);
			pago=c.getCantidad()*c.getPrecioUnitario();
			ArregloProducto ap=new ArregloProducto();
			Producto p=ap.buscar(c.getCodigoProducto());
			imprimir("                    Cantidad   Precio   Importe");
			imprimir("    "+formato(p.getDetalle())+formato(c.getCantidad())+formato(c.getPrecioUnitario())+formato(pago));
			imprimir("Pago de  Producto"+ pago);
		}
		imprimir("---------------------------------------------------");
		imprimir("Pago de  ingreso :"+in.getcostoIngreso());
		imprimir("-----------------------------------------------------");
		 pagoTotal=pago+in.getcostoIngreso();
		 imprimir("PAGO TOTAL : "+pagoTotal);
	}
		
	int leerCodigo(){
		return Integer.parseInt(cboCodigoIngresoP.getSelectedItem().toString());
	}	
	int leerCodigoboleta(){
		return Integer.parseInt(txtBoleta.getText());
	}
	
	void colocarCodigosingresos() {
		Ingresos i;
		for (int j=0; j<ai.tamaño(); j++) {
			i = ai.obtener(j);
			if (i.getEstado() == 1)
				cboCodigoIngresoP.addItem("" + i.getcodigoIngreso());
		}	
	}
	void imprimirEncabezado(){
		txtS.setText("");
		imprimir("   HOTEL HILTON  - BOLETA DE PAGO");
		imprimir("      DEL " + fechaBoleta() + " - " + horaBoleta());
		imprimir(" ===================================");
	}
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	String fechaBoleta() {
		Calendar calendario = new GregorianCalendar();
		return String.format("%02d/",calendario.get(Calendar.DAY_OF_MONTH)) +
			   String.format("%02d/",calendario.get(Calendar.MONTH) + 1) +
			   calendario.get(Calendar.YEAR);
	}
	String horaBoleta() {
		Calendar calendario = new GregorianCalendar();
		return String.format("%02d:",calendario.get(Calendar.HOUR_OF_DAY)) +
			   String.format("%02d:",calendario.get(Calendar.MINUTE)) +
			   String.format("%02d",calendario.get(Calendar.SECOND));
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
