package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloBungalow;
import clases.Bungalow;
import libreria.Lib;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;

public class JdBungalow extends JDialog implements ActionListener {
	private JLabel lblNumeroBungalow;
	private JLabel lblCategoria;
	private JLabel lblPrecioXDia;
	private JLabel lblEstado;
	private JTextField txtNumBun;
	private JTextField txtPrecioPorDia;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblbung;
	private JButton btnConsultar;
	private DefaultTableModel modelo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdBungalow dialog = new JdBungalow();
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
	public JdBungalow() {
		setTitle("Mantenimiento||Bungalow");
		setBounds(100, 100, 641, 474);
		getContentPane().setLayout(null);

		lblNumeroBungalow = new JLabel("Numero Bungalow");
		lblNumeroBungalow.setBounds(12, 13, 118, 16);
		getContentPane().add(lblNumeroBungalow);

		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 42, 56, 16);
		getContentPane().add(lblCategoria);

		lblPrecioXDia = new JLabel("Precio/Dia");
		lblPrecioXDia.setBounds(12, 71, 79, 16);
		getContentPane().add(lblPrecioXDia);

		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(12, 100, 56, 16);
		getContentPane().add(lblEstado);

		txtNumBun = new JTextField();
		txtNumBun.setBounds(124, 10, 116, 22);
		getContentPane().add(txtNumBun);
		txtNumBun.setColumns(10);

		txtPrecioPorDia = new JTextField();
		txtPrecioPorDia.setBounds(124, 68, 116, 22);
		getContentPane().add(txtPrecioPorDia);
		txtPrecioPorDia.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(339, 96, 97, 25);
		getContentPane().add(btnAceptar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(496, 9, 97, 25);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(496, 38, 97, 25);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(496, 96, 97, 25);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 141, 599, 273);
		getContentPane().add(scrollPane);

		tblbung = new JTable();
		tblbung.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblbung);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(496, 67, 97, 25);
		getContentPane().add(btnConsultar);
		modelo = new DefaultTableModel();
		modelo.addColumn("NUMERO BUNGALOW");
		modelo.addColumn("CATEGORIA");
		modelo.addColumn("ESTADO");
		modelo.addColumn("PRECIO/DIA");
		tblbung.setModel(modelo);
		
		cboCategoria = new JComboBox<String>();
		cboCategoria.setModel(new DefaultComboBoxModel<String>(Lib.CategoriaBungalow));
		cboCategoria.setBounds(124, 40, 118, 16);
		getContentPane().add(cboCategoria);

		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<String>(Lib.estadosBungalow));
		cboEstado.setBounds(124, 98, 116, 18);
		getContentPane().add(cboEstado);
		habilitarEntradas(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}

	// DEC GLOBAL
	ArregloBungalow ab = new ArregloBungalow();
	int numeroConsultado = ab.obtener(0).getNumeroBungalow();
	private JComboBox<String> cboCategoria;
	private JComboBox<String> cboEstado;
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		btnAdicionar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(true);
		btnEliminar.setEnabled(true);
		habilitarEntradas(true);
		limpieza();
		txtPrecioPorDia.requestFocus();

	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ab.tamaño() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen Bungalows");
		}
		else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
			txtNumBun.requestFocus();
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (ab.tamaño() == 0)
			mensaje("No existen Bungalows");
		else {
			editarFila();
			habilitarEntradas(false);
			int ok = confirmar("¿ Desea eliminar el registro ?");
			if (ok == 0) {
				ab.eliminar(ab.buscar(leerNumBun()));
				listar();
				editarFila();
			}
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		Bungalow x = ab.buscar(leerNumBun());
		numeroConsultado = x.getNumeroBungalow();
		txtPrecioPorDia.setText("" + x.getPrecioPorDia());
		cboCategoria.setSelectedIndex(x.getCategoria());
		cboEstado.setSelectedIndex(x.getEstado());
		txtNumBun.requestFocus();
	}

	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		int numeroBungalow = leerNumBun();
		if (numeroBungalow >= 0) {
			int categoria = leerCategoria();
			if (categoria >= 0)
				try {
					double precioPorDia = leerPrecioxDia();
					int estado = leerEstado();
					if (btnAdicionar.isEnabled() == false) {
						Bungalow nuevo = new Bungalow(numeroBungalow, categoria, precioPorDia, estado);
						ab.adicionar(nuevo);
						btnAdicionar.setEnabled(true);
					}
					if (btnModificar.isEnabled() == false) {
						Bungalow x = ab.buscar(numeroBungalow);
						x.setNumeroBungalow(numeroBungalow);
						x.setPrecioPorDia(precioPorDia);
						x.setEstado(estado);
						x.setCategoria(categoria);
						btnModificar.setEnabled(true);
					}
					listar();
					habilitarEntradas(false);
				}
				catch (Exception x) {
					error("Ingrese 	Precio/Dia correcto", txtPrecioPorDia);
				}
		}
		else
			error("Ingrese 	Numero de Bungalow correcto", txtNumBun);

	}

	////////////
	// Métodos tipo void (sin parámetros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblbung.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(20)); // Numero Bungalow
		tcm.getColumn(1).setPreferredWidth(anchoColumna(20)); // Categoria
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20)); // Precio/Dia
		tcm.getColumn(3).setPreferredWidth(anchoColumna(20)); // Estado
	}

	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblbung.getSelectedRow();
		if (modelo.getRowCount() == ab.tamaño() - 1)
			posFila = ab.tamaño() - 1;
		if (posFila == ab.tamaño())
			posFila--;
		modelo.setRowCount(0);
		Bungalow b;
		for (int i = 0; i < ab.tamaño(); i++) {
			b = ab.obtener(i);
			Object[] fila = { b.getNumeroBungalow(), enTextoCategoria(b.getCategoria()), enTextoEstado(b.getEstado()), b.getPrecioPorDia() };
			modelo.addRow(fila);
		}
		if (ab.tamaño() > 0)
			tblbung.getSelectionModel().setSelectionInterval(posFila, posFila);
	}

	void editarFila() {
		if (ab.tamaño() == 0)
			limpieza();
		else {
			Bungalow b = ab.obtener(tblbung.getSelectedRow());
			txtNumBun.setText("" + b.getNumeroBungalow());
			cboCategoria.setSelectedIndex(b.getCategoria());
			cboEstado.setSelectedIndex(b.getEstado());
			txtPrecioPorDia.setText("" + b.getPrecioPorDia());
		}
	}

	void limpieza() {
		txtNumBun.setText("" + ab.numeroCorrelativo());
		cboCategoria.setSelectedIndex(0);
		;
		cboEstado.setSelectedIndex(0);
		txtPrecioPorDia.setText("");
	}

	// Métodos tipo void (con parámetros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	void habilitarEntradas(boolean sino) {
		txtPrecioPorDia.setEditable(sino);
	}

	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}

	// Métodos que retornan valor (sin parámetros)
	int leerNumBun() {
		return Integer.parseInt(txtNumBun.getText().trim());
	}

	int leerEstado() {
		return cboEstado.getSelectedIndex();
	}

	int leerCategoria() {
		return cboCategoria.getSelectedIndex();
	}

	double leerPrecioxDia() {
		return Double.parseDouble(txtPrecioPorDia.getText().trim());
	}

	// Métodos que retornan valor (con parámetros)
	String enTextoCategoria(int i) {
		return cboCategoria.getItemAt(i);
	}

	String enTextoEstado(int i) {
		return cboEstado.getItemAt(i);
	}

	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
}