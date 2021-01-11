package GUI;

import libreria.*;

import clases.*;
import arreglos.*;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class JdHospedaje extends JDialog implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tbhospedaje;
	private JLabel lblHospedaje;
	private JLabel lblIngreso;
	private JLabel lblBungalow;
	private JTextField txtHospedaje;
	private JComboBox<String> cboCodIngreso;
	private JComboBox<String> cboBungalow;
	private JButton btnProceder;
	private JButton btnHospedar;
	private DefaultTableModel modelo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdHospedaje dialog = new JdHospedaje();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JdHospedaje() {
		setTitle("Registro||Hospedaje");
		setBounds(100, 100, 772, 570);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 125, 736, 385);
		contentPanel.add(scrollPane);
		
		tbhospedaje = new JTable();
		tbhospedaje.addKeyListener(this);
		tbhospedaje.addMouseListener(this);
		tbhospedaje.setFillsViewportHeight(true);
		scrollPane.setViewportView(tbhospedaje);
	   modelo=new DefaultTableModel(); 
	   modelo = new DefaultTableModel();
		modelo.addColumn("Código hospedaje");
		modelo.addColumn("Código Ingreso");
		modelo.addColumn("Nro bungalow");
		modelo.addColumn("Fecha de salida");
		modelo.addColumn("Hora de salida");
		modelo.addColumn("Costo de hospedaje");
		modelo.addColumn("Estado");
		tbhospedaje.setModel(modelo);
		lblHospedaje = new JLabel("Hospedaje");
		lblHospedaje.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHospedaje.setBounds(10, 19, 80, 20);
		contentPanel.add(lblHospedaje);
		
		lblIngreso = new JLabel("Ingreso");
		lblIngreso.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIngreso.setBounds(214, 22, 46, 14);
		contentPanel.add(lblIngreso);
		
		lblBungalow = new JLabel("Bungalow");
		lblBungalow.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBungalow.setBounds(10, 50, 61, 14);
		contentPanel.add(lblBungalow);
		
		txtHospedaje = new JTextField();
		txtHospedaje.setEditable(false);
		txtHospedaje.setBounds(100, 20, 86, 20);
		contentPanel.add(txtHospedaje);
		txtHospedaje.setColumns(10);
		
		cboCodIngreso = new JComboBox<String>();
		cboCodIngreso.setBounds(287, 21, 86, 22);
		contentPanel.add(cboCodIngreso);
		visualizarIngreso();
		
		cboBungalow = new JComboBox<String>();
		cboBungalow.setBounds(100, 48, 87, 20);
		contentPanel.add(cboBungalow);
		visualizarNroBungalow();
		
		btnProceder = new JButton("Proceder");
		btnProceder.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnProceder.addActionListener(this);
		btnProceder.setBounds(383, 18, 86, 28);
		contentPanel.add(btnProceder);
		txtHospedaje.setText("" +  ah.codigoCorrelativo());
		btnHospedar = new JButton("Hospedar");
		btnHospedar.addActionListener(this);
		btnHospedar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnHospedar.setBounds(649, 11, 97, 90);
		contentPanel.add(btnHospedar);
		
		listar();
		editarFila();
	}
	
	ArregloIngresos ai = new ArregloIngresos();
	ArregloBungalow ab = new ArregloBungalow();
	ArregloHospedaje ah = new ArregloHospedaje();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnHospedar) {
			actionPerformedBtnHospedar(arg0);
		}
		if (arg0.getSource() == btnProceder) {
			actionPerformedBtnProceder(arg0);
		}
	}
	protected void actionPerformedBtnProceder(ActionEvent arg0) {
		
			int codigoHospedaje = leerCodigoHospedaje();
			int codigoIngreso = leerCodigoIngreso();
			int numeroBungalow = leerNumeroBungalow();
			int ok = confirmar("Desea Hospedar");
			if(ok == 0){
			Bungalow b = ab.buscar(numeroBungalow);
			Hospedajes nuevo = new Hospedajes(codigoHospedaje, codigoIngreso, numeroBungalow,"", "", 0, 0);
			ah.adicionar(nuevo);
			ah.actualizarDatos();
			
			b.setEstado(1);
			ab.actualizarDatos();
			cboCodIngreso.removeItem(cboCodIngreso.getSelectedItem());
			cboBungalow.removeItem(cboBungalow.getSelectedItem());
			
			}
			listar();
			editarFila();
			}
			
	

	protected void actionPerformedBtnHospedar(ActionEvent arg0) {
		if(cboCodIngreso.getSelectedIndex()<0)
			mensaje("Todos los socios están hospedados");
		else 
			if(cboBungalow.getSelectedIndex()<0)
				mensaje("Todos los Bungalows están ocupados");
			else{
				txtHospedaje.setText("" +  ah.codigoCorrelativo());
			}
	}
	
	void listar(){ 
		int posfila = 0;
		if (modelo.getRowCount() > 0)
			posfila = tbhospedaje.getSelectedRow();
		if (modelo.getRowCount() == ah.tamaño() - 1)
			posfila = ah.tamaño() - 1;
		if (posfila == ah.tamaño())
			posfila --;
		modelo.setRowCount(0);
		Hospedajes h;
		for(int i=0;i< ah.tamaño();i++){
		h=ah.obtener(i); 
		Object[] lista={ h.getCodigoHospedaje(),  
				   h.getCodigoIngreso(), 
				   h.getnumeroBungalow(),
		           h.getfechaSalida(), 
		           h.gethoraSalida(), 
		           h.getcostoHospedaje(), 
		           Lib.estadosHospedaje[h.getEstado()]};
		    modelo.addRow(lista);
		} 
		if (ai.tamaño() > 0)
			tbhospedaje.getSelectionModel().setSelectionInterval(posfila, posfila);
	}
	
	void editarFila() {
		if (ah.tamaño() == 0)
			txtHospedaje.setText("" + ah.codigoCorrelativo());
		else {
			Hospedajes h = ah.obtener(tbhospedaje.getSelectedRow());
			txtHospedaje.setText("" + h.getCodigoHospedaje());
			
		}
	}
	
	
	void visualizarNroBungalow(){
		ArregloBungalow ab = new ArregloBungalow();
		Bungalow b;
		for (int i=0; i<ab.tamaño(); i++) {
			b = ab.obtener(i);
			if (b.getEstado() == 0)
				cboBungalow.addItem("" + b.getNumeroBungalow());
		}
	}
	void visualizarIngreso(){
		Ingresos i;
		for(int j=0; j< ai.tamaño(); j++){
			i = ai.obtener(j);
			if(ah.procedeCodigoIngreso(i.getcodigoIngreso()))
				cboCodIngreso.addItem("" + i.getcodigoIngreso());
		}
	}
	
	
	int leerCodigoHospedaje(){
		return Integer.parseInt(txtHospedaje.getText().trim());
	}
	int leerCodigoIngreso(){
		return Integer.parseInt(cboCodIngreso.getSelectedItem().toString());
	}
	int leerNumeroBungalow(){
		return Integer.parseInt(cboBungalow.getSelectedItem().toString());
	}
//  Métodos que retornan valor (con parámetros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	int confirmar(String s1, String s2) {
		return JOptionPane.showConfirmDialog(this, s1, s2, 0, 1, null);
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tbhospedaje) {
			mouseClickedTbhospedaje(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTbhospedaje(MouseEvent arg0) {
		
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == tbhospedaje) {
			keyReleasedTbhospedaje(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTbhospedaje(KeyEvent arg0) {
		arg0.consume();
		editarFila();
		
	}
} 
