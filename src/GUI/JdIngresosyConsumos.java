package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import arreglos.ArregloConsumo;
import arreglos.ArregloIngresos;
import arreglos.ArregloProducto;
import arreglos.ArregloSocio;
import clases.Consumo;
import clases.Ingresos;
import clases.Producto;
import clases.Socio;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class JdIngresosyConsumos extends JDialog implements ActionListener {
	private JLabel lblCodigoIngreso;

	private JTextArea txtS;
	private JButton btnPagar;
	private JComboBox<Object> cboCodigoIngreso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdIngresosyConsumos dialog = new JdIngresosyConsumos();
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
	public JdIngresosyConsumos() {
		setBounds(100, 100, 677, 427);
		setTitle("Pago||Ingresos y Consumos");
		getContentPane().setLayout(null);
		
		lblCodigoIngreso = new JLabel("Codigo");
		lblCodigoIngreso.setBounds(12, 13, 110, 23);
		getContentPane().add(lblCodigoIngreso);
		

		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtS.setBounds(12, 49, 635, 318);
		getContentPane().add(txtS);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(this);
		btnPagar.setBounds(530, 12, 97, 25);
		getContentPane().add(btnPagar);
		
		cboCodigoIngreso = new JComboBox<Object>();
		cboCodigoIngreso.addActionListener(this);
		cboCodigoIngreso.setBounds(88, 13, 167, 22);
		getContentPane().add(cboCodigoIngreso);
		colocarCodigosConsultas();
	}
	ArregloIngresos ai=new ArregloIngresos();
	ArregloSocio as=new ArregloSocio();
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboCodigoIngreso) {
			actionPerformedCboCodigoIngreso(arg0);
		}
		if (arg0.getSource() == btnPagar) {
			actionPerformedBtnPagar(arg0);
		}
	}
	protected void actionPerformedCboCodigoIngreso(ActionEvent arg0) {
		txtS.setText("");
			if(cboCodigoIngreso.getSelectedIndex()>=0)
				listar();
	
	}
	
	protected void actionPerformedBtnPagar(ActionEvent arg0) {
		if(cboCodigoIngreso.getSelectedIndex()>=0){
			int ok=confirmar("¿Desea pagar Atencion?");
			if(ok==0){
				Ingresos i =ai.buscar(leerCodigoIngreso());
				i.setEstado(1);
				ai.actualizarDatos();
				cboCodigoIngreso.removeItem(cboCodigoIngreso.getSelectedItem());
			}
		}
		else mensaje("No existen pagos  pendientes");
	}
//  Métodos tipo void (sin parámetros)
	void colocarCodigosConsultas() {
		Ingresos a;
		for (int i=0; i<ai.tamaño(); i++) {
			a = ai.obtener(i);
			if (a.getEstado() == 0)
				cboCodigoIngreso.addItem("" + a.getcodigoIngreso());
		}	
	}
	void imprimir(){
		imprimir("");
	}
	void  listar(){
			Ingresos in=ai.buscar(leerCodigoIngreso());
			Socio s=as.buscar(in.getcodigoSocio());
				imprimir("Socio : "+s.getCodigoSocio());
				imprimir("Nombres : "+s.getNombres());
				imprimir("Apellidos : "+s.getApellidos());
				imprimir();
				ArregloConsumo ac=new ArregloConsumo(""+leerCodigoIngreso());
				Consumo c;
				imprimir("1)Consumo");
			
				imprimir("               Cantidad Precio Importe");
				double  pago;
				for(int i=0;i<ac.tamaño();i++){
					c=ac.obtener(i);
					pago=c.getCantidad()*c.getPrecioUnitario();
					ArregloProducto ap=new ArregloProducto();
					Producto p=ap.buscar(c.getCodigoProducto());
					imprimir("  "+formato(p.getDetalle())+formato(c.getCantidad())+formato(c.getPrecioUnitario())+formato(pago));
				}
				imprimir();
				imprimir("TOTAL  A PAGAR S/ "+formato(in.getcostoIngreso()));
				
	}
	//METODOS  TIPO  VOID (CON PARAMETROS)
	void imprimir(String s){
		txtS.append(s+"\n");
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	//Metodos  que  retornan valor  (sin parametros)
	int leerCodigoIngreso(){
		return  Integer.parseInt(cboCodigoIngreso.getSelectedItem().toString());
	}
	//metodos  que retornan valor(Con parametros)
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
