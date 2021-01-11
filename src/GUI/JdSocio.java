package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloSocio;
import clases.Socio;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class JdSocio extends JDialog implements ActionListener {
	private JLabel lblImgSocio;
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JComboBox<String> cboOperacion;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellidos;
	private JTextField txtApellido;
	private JLabel lblDni;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JLabel lblTelefono;
	private JScrollPane scrollPane;
	private JTable tblSocio;
	private JButton btnOk;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdSocio dialog = new JdSocio();
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
	public JdSocio() {
		setTitle("Mantenimiento||Socio");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);

		lblImgSocio = new JLabel();
		lblImgSocio.setIcon(new ImageIcon("imagenes/socio.png"));
		lblImgSocio.setBounds(412, 42, 88, 89);
		getContentPane().add(lblImgSocio);

		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(427, 13, 56, 16);
		getContentPane().add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(475, 10, 94, 22);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		cboOperacion = new JComboBox<String>();
		cboOperacion.setModel(new DefaultComboBoxModel<String>(new String[] { "Adicionar", "Consultar", "Modificar", "Eliminar" }));
		cboOperacion.addActionListener(this);
		cboOperacion.setBounds(581, 10, 91, 22);
		getContentPane().add(cboOperacion);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 13, 56, 16);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(80, 10, 116, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(12, 48, 56, 16);
		getContentPane().add(lblApellidos);

		txtApellido = new JTextField();
		txtApellido.setBounds(80, 45, 116, 22);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);

		lblDni = new JLabel("Dni");
		lblDni.setBounds(12, 77, 56, 16);
		getContentPane().add(lblDni);

		txtDni = new JTextField();
		txtDni.setBounds(80, 74, 116, 22);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(80, 109, 116, 22);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(12, 112, 56, 16);
		getContentPane().add(lblTelefono);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 141, 655, 298);
		getContentPane().add(scrollPane);

		tblSocio = new JTable();
		tblSocio.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblSocio);

		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(232, 48, 97, 25);
		getContentPane().add(btnOk);

		modelo = new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO");
		modelo.addColumn("DNI");
		modelo.addColumn("TELEFONO");
		tblSocio.setModel(modelo);
		btnOk.setEnabled(false);
		ajustarAnchoColumnas();
		listar();

	}

	// Declaracion Global
	ArregloSocio as = new ArregloSocio();

	// CODIGO A CONSULTAR
	int codigoConsultado = as.obtener(0).getCodigoSocio();

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnOk) {
			actionPerformedBtnOk(arg0);
		}
		if (arg0.getSource() == cboOperacion) {
			actionPerformedCboOperacion(arg0);
		}
	}

	protected void actionPerformedCboOperacion(ActionEvent arg0) {
		btnOk.setEnabled(true);
		int posOperacion = leerPosOperacion();
		switch (posOperacion) {
			case 0:
				if (as.tamaño() == 0)
					btnOk.setEnabled(true);
				txtCodigo.setEditable(false);
				txtCodigo.setText("" + as.codigoCorrelativo());
				txtNombre.setText("");
				txtApellido.setText("");
				txtDni.setText("");
				txtTelefono.setText("");
				txtNombre.requestFocus();
				HabilitarEntradas(true);
				txtNombre.requestFocus();
				break;
			case 1:
				if (as.tamaño() > 0)
					txtCodigo.setEditable(true);
				txtCodigo.setText("" + codigoConsultado);
				HabilitarEntradas(false);
				consultarSocio();
				txtCodigo.requestFocus();
				break;
			case 2:
				txtCodigo.setEditable(false);
				txtCodigo.setText("" + codigoConsultado);
				HabilitarEntradas(true);
				consultarSocio();
				txtNombre.requestFocus();
				break;
			case 3:
				txtCodigo.setEditable(false);
				txtCodigo.setText("" + codigoConsultado);
				HabilitarEntradas(false);
				consultarSocio();
		}
	}

	protected void actionPerformedBtnOk(ActionEvent arg0) {
		int posOperacion = leerPosOperacion();
		switch (posOperacion) {
			case 0:
				adicionarSocio();
				break;
			case 1:
				consultarSocio();
				break;
			case 2:
				modificarSocio();
				break;
			case 3:
				eliminarSocio();
				break;
		}
	}

	void adicionarSocio() {
		btnOk.setEnabled(true);
		int codigo = leerCodigo();
		String nombre = leerNombre();
		String apellido = leerApellido();
		String dni = leerDni();
		int telefono = leerTelefono();
		Socio nuevo = new Socio(codigo, nombre, apellido, dni, telefono);
		as.adicionar(nuevo);
		listar();
		txtCodigo.setText("" + as.codigoCorrelativo());
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtTelefono.setText("");

	}

	void consultarSocio() {
		try {
			Socio x = as.buscar(leerCodigo());
			if (x != null) {
				codigoConsultado = x.getCodigoSocio();
				txtNombre.setText(x.getNombres());
				txtApellido.setText(x.getApellidos());
				txtDni.setText(x.getDni());
				txtTelefono.setText("" + x.getTelefono());
				txtCodigo.requestFocus();
			}
			else
				error("El codigo" + leerCodigo() + "no existe", txtCodigo);
		}
		catch (Exception e) {
			error("INGRESE CODIGO A CONSULTAR", txtCodigo);
		}
	}

	void modificarSocio() {
		btnOk.setEnabled(true);
		Socio x = as.buscar(codigoConsultado);
		String nombres = leerNombre();
		if (nombres.length() > 0) {
			String apellidos = leerApellido();
			String dni = leerDni();
			try {
				int telefono = leerTelefono();
				x.setNombres(nombres);
				x.setApellidos(apellidos);
				x.setDni(dni);
				x.setTelefono(telefono);
				listar();
			}
			catch (Exception e) {
				error("INGRESE EL TELEFONO CORRECTO", txtTelefono);
			}
		}
		else
			error("IGRESE EL NOMBR CORRECTO", txtNombre);
	}

	void eliminarSocio() {
		btnOk.setEnabled(true);
		int ok = confirmar("¿Desea eliminar el registro?");
		if (ok == 0) {
			Socio x = as.buscar(codigoConsultado);
			as.eliminar(x);
			listar();
			if (as.tamaño() > 0) {
				codigoConsultado = as.obtener(0).getCodigoSocio();
				txtCodigo.setText("" + codigoConsultado);
				consultarSocio();
			}
			else {
				codigoConsultado = as.codigoCorrelativo();
				cboOperacion.setSelectedIndex(0);
			}
		}
	}

	// METODOS VOID (SIN PARAMETROS)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblSocio.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8)); // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(18)); // nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // apellido
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // dni
		tcm.getColumn(4).setPreferredWidth(anchoColumna(15)); // telefono
	}

	void listar() {

		Socio x;
		modelo.setRowCount(0);
		for (int i = 0; i < as.tamaño(); i++) {
			x = as.obtener(i);
			Object[] fila = { x.getCodigoSocio(), x.getNombres(), x.getApellidos(), x.getDni(), x.getTelefono() };
			modelo.addRow(fila);
		}
	}

	// METODOS VOID CON PARAMETROS//
	void HabilitarEntradas(boolean sino) {
		txtNombre.setEditable(sino);
		txtDni.setEditable(sino);
		txtApellido.setEditable(sino);
		txtTelefono.setEditable(sino);
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informacion", 0);

	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();

	}

	// METODOS QUE RETORNAN VALOR (SIN PARAMETRO)
	int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText().trim());
	}

	String leerNombre() {
		return txtNombre.getText().trim();
	}

	String leerApellido() {
		return txtApellido.getText().trim();
	}

	String leerDni() {
		return txtDni.getText().trim();
	}

	int leerTelefono() {
		return Integer.parseInt(txtTelefono.getText().trim());
	}

	int leerPosOperacion() {
		return cboOperacion.getSelectedIndex();
	}

	/// METODOS QUE RETORNAN VALOR
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	String ajustar(double numero) {
		return String.format("5.2f", numero);
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
}
