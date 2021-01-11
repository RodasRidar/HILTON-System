package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloConsumo;
import arreglos.ArregloIngresos;
import arreglos.ArregloProducto;
import arreglos.ArregloSocio;
import clases.Consumo;
import clases.Ingresos;
import clases.Producto;
import clases.Socio;
import libreria.Fecha;
import libreria.Lib;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class JdConsumo extends JDialog implements ActionListener, MouseListener {
	private JLabel lblCodigo;
	private JTextField txtIngreso;
	private JLabel lblSocio;
	private JTextField txtSocio;
	private JLabel lblProducto;
	private JComboBox<String> cboCodigoProducto;
	private JButton btnQuitar;
	private JComboBox<String> cboProducto;
	private JButton btnAgregar;
	private JScrollPane scrollPane;
	private JTable tblIngreso;
	private JComboBox<String> cboCodigoSocio;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdConsumo dialog = new JdConsumo();
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
	public JdConsumo() {
		setTitle("Registro||Consumo");
		setBounds(100, 100, 792, 499);
		getContentPane().setLayout(null);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(12, 13, 56, 16);
		getContentPane().add(lblCodigo);
		
		txtIngreso = new JTextField();
		txtIngreso.setEditable(false);
		txtIngreso.setBounds(72, 10, 116, 22);
		getContentPane().add(txtIngreso);
		txtIngreso.setColumns(10);
		
		lblSocio = new JLabel("Socio");
		lblSocio.addMouseListener(this);
		lblSocio.setBounds(12, 42, 56, 16);
		getContentPane().add(lblSocio);
		
		txtSocio = new JTextField();
		txtSocio.setEditable(false);
		txtSocio.setBounds(72, 39, 116, 22);
		getContentPane().add(txtSocio);
		txtSocio.setColumns(10);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(12, 79, 56, 16);
		getContentPane().add(lblProducto);
		
		cboCodigoProducto = new JComboBox<String>();
		cboCodigoProducto.setBounds(72, 76, 104, 22);
		getContentPane().add(cboCodigoProducto);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(this);
		btnQuitar.setBounds(420, 75, 97, 25);
		getContentPane().add(btnQuitar);
		
		cboProducto = new JComboBox<String>();
		cboProducto.setBounds(311, 76, 97, 22);
		getContentPane().add(cboProducto);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(188, 75, 97, 25);
		getContentPane().add(btnAgregar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 144, 750, 295);
		getContentPane().add(scrollPane);
		
		tblIngreso = new JTable();
		tblIngreso.addMouseListener(this);
		tblIngreso.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblIngreso);
		
		cboCodigoSocio = new JComboBox<String>();
		cboCodigoSocio.addActionListener(this);
		cboCodigoSocio.setBounds(200, 39, 85, 22);
		getContentPane().add(cboCodigoSocio);
		colocarCodigosSocio();
		
		modelo = new DefaultTableModel(); 
		modelo.addColumn("Código");
		modelo.addColumn("Socio"); 
		modelo.addColumn("Invitados");
		modelo.addColumn("Fecha Ingreso"); 
		modelo.addColumn("Hora Ingreso"); 
		modelo.addColumn("Total pagar");
		modelo.addColumn(" Estado"); 
		tblIngreso.setModel(modelo);
		txtIngreso.setEditable(false);
		txtSocio.setEditable(false);
		
		ajustarAnchoColumnas();
		listar();
		editarFila();

		
	}
	//Declaración global
			ArregloIngresos ai= new ArregloIngresos();
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
		if (e.getSource() == btnQuitar) {
			actionPerformedBtnQuitar(e);
		}
		if (e.getSource() == cboCodigoSocio) {
			actionPerformedCboCodigoSocio(e);
		}
	}
	protected void actionPerformedBtnQuitar(ActionEvent e) {
		Ingresos i = ai.buscar(leerCodigoIngreso());
		if (i.getEstado() == 0)
			try {
				int codProducto = leerCodigoProductoRemover();
				int ok = confirmar("¿ Quitar producto del consumo ?");
				if (ok == 0) {
					ArregloConsumo ac = new ArregloConsumo(leerCodigoConsumo());
					Consumo c = ac.buscar(codProducto);
					ac.eliminar(c);
					ac.grabarConsumo();
					ArregloProducto ap = new ArregloProducto();
					Producto p = ap.buscar(codProducto);
					p.setStock(p.getStock() + c.getCantidad());
					ap.actualizarDatos();
					i.setcostoIngreso(redondear(i.getcostoIngreso() - c.getCantidad()*c.getPrecioUnitario()));
					ai.actualizarDatos();
					cboCodigoProducto.addItem(cboProducto.getSelectedItem().toString());
					cboProducto.removeItem(cboProducto.getSelectedItem());
					listar();
				}
			}
			catch (Exception e1) {
				mensaje("El Socio no cuenta con productos .");
			}
		else
			mensaje("No se puede quitar productos porque el consumo está pagado");
	}

	protected void actionPerformedBtnAgregar(ActionEvent e) {
		Ingresos i = ai.buscar(leerCodigoIngreso());
		if (i.getEstado() == 0)
			try {
				int codigoProducto = leerCodigoProducto();
				int ok = confirmar(obtenerDatosProducto(codigoProducto), "¿ Añadir ?");
				if (ok == 0)
					try {
						int cantidad = Integer.parseInt(confirmarIngreso("Cantidad"));
						ArregloProducto ap = new ArregloProducto();
						Producto p = ap.buscar(codigoProducto);
						if (cantidad <= p.getStock()) {
							ArregloConsumo ac = new ArregloConsumo(leerCodigoConsumo());
							ac.adicionar(new Consumo(codigoProducto, cantidad, p.getPrecioUnitario()));;
							ac.grabarConsumo();
							p.setStock(p.getStock() - cantidad);
							ac.grabarConsumo();
							i.setcostoIngreso(i.getcostoIngreso() + cantidad*p.getPrecioUnitario());;
							ai.grabarIngreso();;
							cboProducto.addItem(cboCodigoProducto.getSelectedItem().toString());
							cboCodigoProducto.removeItem(cboCodigoProducto.getSelectedItem());
							listar();
						}
						else
							mensaje("Sólo hay " + p.getStock() + " unidades en stock");
					}
					catch (Exception e1) {
						mensaje("Ingrese CANTIDAD correcta");
					}
			}
			catch (Exception e1) {
				mensaje("El consumo del Socio tiene todos los productos");
			}
		else
			mensaje("No se puede agregar Productos  el  Ingreso  ya  esta pagado");
	}
//  Métodos tipo void (sin parámetros)
		void colocarCodigosSocio() {
			ArregloSocio as = new ArregloSocio();
			Socio s;
			for (int i=0; i<as.tamaño(); i++) {
				s = as.obtener(i);
					cboCodigoSocio.addItem("" + s.getCodigoSocio());
			}
		}
		void ajustarAnchoColumnas() {
			TableColumnModel tcm = tblIngreso.getColumnModel();
			tcm.getColumn(0).setPreferredWidth(anchoColumna(13));  // codigoAtencion
			tcm.getColumn(1).setPreferredWidth(anchoColumna(13));  // codigoPaciente
			tcm.getColumn(2).setPreferredWidth(anchoColumna(13));  // invitados
			tcm.getColumn(3).setPreferredWidth(anchoColumna(22));  // fechaAtencion
			tcm.getColumn(4).setPreferredWidth(anchoColumna(13));  // horaAtencion
			tcm.getColumn(5).setPreferredWidth(anchoColumna(13));  // totalPagar
			tcm.getColumn(6).setPreferredWidth(anchoColumna(13));  // estado
		}
		void distribuirCodigosProductos() {
			ArregloConsumo ac = new ArregloConsumo(leerCodigoConsumo());
			Consumo c;
			cboProducto.removeAllItems();
			for (int i=0; i<ac.tamaño(); i++) {
				c = ac.obtener(i);
				cboProducto.addItem("" + c.getCodigoProducto());
			}
			ArregloProducto ap = new ArregloProducto();
			Producto p;
			cboCodigoProducto.removeAllItems();
			for (int i=0; i<ap.tamaño(); i++) {
				p = ap.obtener(i);
				if (ac.buscar(p.getCodigoProducto()) == null)
					cboCodigoProducto.addItem("" + p.getCodigoProducto());
			}	
		}
		void listar() {
			int posFila = 0;
			if (modelo.getRowCount() > 0)
				posFila = tblIngreso.getSelectedRow();
			if (modelo.getRowCount() == ai.tamaño() - 1)
				posFila = ai.tamaño() - 1;
			if (posFila == ai.tamaño())
				posFila --;
			modelo.setRowCount(0);
			Ingresos i;
			for (int j=0; j<ai.tamaño(); j++) {
				i = ai.obtener(j);
				Object[] fila = {	 i.getcodigoIngreso(),
											i.getcodigoSocio(),
											i.getnumeroInvitados(),
											Fecha.enTextoFecha(i.getfechaIngreso()),
											i.gethoraIngreso(),
											i.getcostoIngreso(),
											Lib.estadosIngreso[i.getEstado()] };
				modelo.addRow(fila);
			}
			if (ai.tamaño() > 0)
				tblIngreso.getSelectionModel().setSelectionInterval(posFila, posFila);
		}
		void editarFila() {
			if (ai.tamaño() == 0)
				txtIngreso.setText("" + ai.codigoCorrelativo());
			else {
				Ingresos i = ai.obtener(tblIngreso.getSelectedRow());
				txtIngreso.setText("" + i.getcodigoIngreso());
				txtSocio.setText("" + i.getcodigoSocio());
				distribuirCodigosProductos();
			}
		}
	//  Métodos que retornan valor (con parámetros)
			int anchoColumna(int porcentaje) {
				return porcentaje * scrollPane.getWidth() / 100;
			}
			int confirmar(String s1, String s2) {
				return JOptionPane.showConfirmDialog(this, s1, s2, 0, 1, null);
			}
			int confirmar(String s) {
				return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
			}
			String confirmarIngreso(String s) {
				return JOptionPane.showInputDialog(this, "", s, 3);
			}
		
		//  Métodos que retornan valor (sin parámetros)
		String obtenerDatosSocio() {
			ArregloSocio as = new ArregloSocio();
		    Socio s = as.buscar(leerCodigoSocio());
		    return "Nombres :  " + s.getNombres() + "\n" +
			       "Apellidos :  " + s.getApellidos();
		}
		double redondear(double real) {
			return Math.round(real * 100) / 100.0;
		}
		int leerCodigoSocio(){
			return Integer.parseInt( txtSocio.getText());
		}
		int leerCodigoIngreso(){
			return  Integer.parseInt(txtIngreso.getText());
		}
		String leerCodigoConsumo(){
			return "" + leerCodigoIngreso();
		}
		int leerCodigoProducto() {
			return Integer.parseInt(cboCodigoProducto.getSelectedItem().toString());
		}
		int leerCodigoProductoRemover(){
			return Integer.parseInt(cboProducto.getSelectedItem().toString());
		}
		String obtenerDatosProducto(int codigoProducto) {
			ArregloProducto ap = new ArregloProducto();
		    Producto p = ap.buscar(codigoProducto);
		    return "Detalle :  " + p.getDetalle() + "\n" +
			       "Precio unitario :  S/ " + p.getPrecioUnitario();
		}

		//Métodos tipo void(con parámetros)
			void mensaje(String s) {
				JOptionPane.showMessageDialog(this, s, "Información", 1);
			}
		
			protected void actionPerformedCboCodigoSocio(ActionEvent e) {
				txtSocio.setText(""+cboCodigoSocio.getSelectedItem());
			}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == lblSocio) {
			mouseClickedLblSocio(e);
		}
		if (e.getSource() == tblIngreso) {
			mouseClickedTblIngreso(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblIngreso(MouseEvent e) {
		 	editarFila();
	}
	protected void mouseClickedLblSocio(MouseEvent e) {
		mensaje(obtenerDatosSocio()); 
	}
}
