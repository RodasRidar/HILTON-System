package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloIngresos;
import arreglos.ArregloSocio;
import clases.Ingresos;
import clases.Socio;
import libreria.Lib;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class JdIngreso extends JDialog implements ActionListener {
	private JLabel lblIngreso;
	private JTextField txtCodIngreso;
	private JLabel lblSocio;
	private JTextField txtInvitados;
	private JLabel lblInvitados;
	private JButton btnIngresar;
	private JScrollPane scrollPane;
	private JTable tblIngreso;
	private DefaultTableModel modelo;
	private JComboBox<String> cboCodigosSocios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdIngreso dialog = new JdIngreso();
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
	public JdIngreso() {
		setTitle("Registro||Ingreso");
		setBounds(100, 100, 660, 458);
		getContentPane().setLayout(null);
		
		lblIngreso = new JLabel("Ingreso:");
		lblIngreso.setBounds(12, 11, 56, 16);
		getContentPane().add(lblIngreso);
		
		txtCodIngreso = new JTextField();
		txtCodIngreso.setEditable(false);
		txtCodIngreso.setText("40001");
		txtCodIngreso.setBounds(129, 8, 79, 22);
		getContentPane().add(txtCodIngreso);
		txtCodIngreso.setColumns(10);
		
		lblSocio = new JLabel("Socio:");
		lblSocio.setBounds(12, 46, 56, 16);
		getContentPane().add(lblSocio);
		
		txtInvitados = new JTextField();
		txtInvitados.setBounds(129, 82, 79, 22);
		getContentPane().add(txtInvitados);
		txtInvitados.setColumns(10);
		
		lblInvitados = new JLabel("Numero Invitados:");
		lblInvitados.setBounds(10, 85, 105, 16);
		getContentPane().add(lblInvitados);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(508, 43, 122, 22);
		getContentPane().add(btnIngresar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 128, 618, 270);
		getContentPane().add(scrollPane);
		
		tblIngreso = new JTable();
		tblIngreso.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblIngreso);
		modelo=new DefaultTableModel();
		modelo.addColumn("Codigo Ingreso");
		modelo.addColumn("Codigo Socio");
		modelo.addColumn("Fecha Ingreso");
		modelo.addColumn("Hora Ingreso");
		modelo.addColumn("Invitados");
		modelo.addColumn("Costo");
		modelo.addColumn("Estado");
		tblIngreso.setModel(modelo);
		cboCodigosSocios = new JComboBox<String>();
		cboCodigosSocios.setBounds(129, 41, 79, 30);
		getContentPane().add(cboCodigosSocios);
		txtCodIngreso.setText(""+ai.codigoCorrelativo());
		colocarCodigosPacientes();
		
		ajustarAnchoColumnas();
		listar();
		
		
	}
	ArregloIngresos ai=new ArregloIngresos();
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(arg0);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent arg0) {
		int codigoIngreso=leerCodigoIngreso();
		int codigoSocio=leerCodigoSocio();
		String fechaIngreso=fechaActual();
		String horaIngreso=horaActual();
		int numeroInvitados=leerNumeroInvitados();
		double costoIngreso=CostoInvitado();
		Ingresos nuevo =new Ingresos(codigoIngreso, codigoSocio, fechaIngreso, horaIngreso, numeroInvitados, costoIngreso, 0);
		ai.adicionar(nuevo);
		listar();
		txtCodIngreso.setText(""+ai.codigoCorrelativo());
		txtInvitados.setText("");
		
	}
	//metodos void  sin parametros
	void colocarCodigosPacientes() {
		ArregloSocio as = new ArregloSocio();
		Socio s;
		for (int i=0; i<as.tamaño(); i++) {
			s = as.obtener(i);
			if (ai.procedeCodigoSocio(s.getCodigoSocio()))
				cboCodigosSocios.addItem("" + s.getCodigoSocio());
			}
		}
	void listar(){
		Ingresos x;
		modelo.setRowCount(0);
			for(int i=0;i<ai.tamaño();i++){
				x=ai.obtener(i);
				Object[]fila={ x.getcodigoIngreso(),
								x.getcodigoSocio(),
								x.getfechaIngreso(),
								x.gethoraIngreso(),
								x.getnumeroInvitados(),
								x.getcostoIngreso(),
								Lib.estadosIngreso[x.getEstado()]};
				modelo.addRow(fila);
				}
			}
			
			void ajustarAnchoColumnas(){
				TableColumnModel tcm=tblIngreso.getColumnModel();
				tcm.getColumn(0).setPreferredWidth(anchoColumna( 8));  
				tcm.getColumn(1).setPreferredWidth(anchoColumna(18));  
				tcm.getColumn(2).setPreferredWidth(anchoColumna(10));  
				tcm.getColumn(3).setPreferredWidth(anchoColumna(10));  
				tcm.getColumn(4).setPreferredWidth(anchoColumna(15));
				tcm.getColumn(5).setPreferredWidth(anchoColumna(15));
				tcm.getColumn(6).setPreferredWidth(anchoColumna(15));
				
			
	}
	
	//METODOS QUE RETORNAN VALOR SIN PARAMETRO
	
	int leerCodigoIngreso(){
		return Integer.parseInt(txtCodIngreso.getText().trim());
	}
	int leerNumeroInvitados(){
		return Integer.parseInt(txtInvitados.getText().trim());
	}
	
	double CostoInvitado(){
		return leerNumeroInvitados()*25;
	}
	int leerCodigoSocio(){
		return Integer.parseInt(cboCodigosSocios.getSelectedItem().toString());
	}
	String fechaActual() {
		int dd, mm, aa;
		Calendar c = new GregorianCalendar();
		dd = c.get(Calendar.DAY_OF_MONTH);
		mm = c.get(Calendar.MONTH) + 1;
		aa = c.get(Calendar.YEAR);
		return ajustar(dd) + "/" + ajustar(mm) + "/" + aa;
	}
	String horaActual() {
		int hh, mm, ss;
		Calendar c = new GregorianCalendar();
		hh = c.get(Calendar.HOUR_OF_DAY);
		mm = c.get(Calendar.MINUTE);
		ss = c.get(Calendar.SECOND);
		return ajustar(hh) + ":" + ajustar(mm) + ":" + ajustar(ss);
	}
	
	//METODOS QUE  RETORNAN VALOR
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	String ajustar(int numero) {
		return String.format("%02d", numero);
	}
}
