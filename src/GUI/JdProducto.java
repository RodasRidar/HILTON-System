package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloProducto;
import clases.Producto;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JdProducto extends JDialog implements ActionListener {
	private JLabel lblProducto;
	private JLabel lblDetalle;
	private JLabel lblPrecio;
	private JLabel lblStock;
	private JTextField txtCodigo;
	private JTextField txtDetalle;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JButton btnOk;
	private JComboBox<String> cboOperacion;
	private JScrollPane scrollPane;
	private JTable tblProducto;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdProducto dialog = new JdProducto();
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
	public JdProducto() {
		setBounds(100, 100, 549, 408);
		setTitle("Mantenimiento||Producto");
		getContentPane().setLayout(null);
		
		lblProducto = new JLabel("Codigo");
		lblProducto.setBounds(12, 13, 56, 16);
		getContentPane().add(lblProducto);
		
		lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(12, 42, 56, 16);
		getContentPane().add(lblDetalle);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(12, 71, 56, 16);
		getContentPane().add(lblPrecio);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(12, 100, 56, 16);
		getContentPane().add(lblStock);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(60, 10, 87, 22);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDetalle = new JTextField();
		txtDetalle.setBounds(60, 42, 116, 22);
		getContentPane().add(txtDetalle);
		txtDetalle.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(60, 71, 116, 22);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(60, 97, 116, 22);
		getContentPane().add(txtStock);
		txtStock.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(203, 42, 97, 25);
		getContentPane().add(btnOk);
		
		cboOperacion = new JComboBox<String>();
		cboOperacion.addActionListener(this);
		cboOperacion.setModel(new DefaultComboBoxModel<String>(new String[] {"Adicionar", "Consultar", "Modificar", "Eliminar"}));
		cboOperacion.setBounds(340, 10, 147, 22);
		getContentPane().add(cboOperacion);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 137, 507, 211);
		getContentPane().add(scrollPane);
		
		tblProducto = new JTable();
		tblProducto.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProducto);
		
		modelo=new DefaultTableModel();
		modelo.addColumn("Codigo");
		modelo.addColumn("Detalle");
		modelo.addColumn("Precio");
		modelo.addColumn("Stock");
		tblProducto.setModel(modelo);
		btnOk.setEnabled(false);
		ajustarAnchoColumnas();
		listar();
	}
	//DECLARACION GLOBAL
	ArregloProducto ap=new ArregloProducto();
	//Codigo Consultar
	int  codigoConsultado=ap.obtener(0).getCodigoProducto();
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboOperacion) {
			actionPerformedCboOperacion(arg0);
		}
		if (arg0.getSource() == btnOk) {
			actionPerformedBtnOk(arg0);
		}
	}
	protected void actionPerformedBtnOk(ActionEvent arg0) {
		int posOperacion=leerPosOperacion();
		switch(posOperacion){
			case 0:
				adicionarProducto();
				break;
			case 1: 
				consultarProducto();
				break;
			case 2: 
				modificarProducto();
				break;
			case 3:
				eliminarProducto();
				break;
		}
	}
	protected void actionPerformedCboOperacion(ActionEvent arg0) {
		int posOperacion=leerPosOperacion();
		switch(posOperacion){
			case 0:
				if(ap.tamaño()==0)
					btnOk.setEnabled(true);
				txtCodigo.setEditable(false);
				txtCodigo.setText(""+ap.codigoCorrelativo());
				txtDetalle.setText("");
				txtPrecio.setText("");
				txtStock.setText("");
				txtDetalle.requestFocus();
				HabilitarEntrada(true);
				txtDetalle.requestFocus();
				break;
			case 1: 
				if(ap.tamaño()>0)
					txtCodigo.setEditable(true);
				txtCodigo.setText(""+codigoConsultado);
				HabilitarEntrada(false);
				consultarProducto();
				txtCodigo.requestFocus();
				break;
			case 2: 
				txtCodigo.setEditable(false);
				txtCodigo.setText(""+codigoConsultado);
				HabilitarEntrada(true);
				consultarProducto();
				txtDetalle.requestFocus();
				break;
			case 3:
				txtCodigo.setEditable(false);
				txtCodigo.setText(""+codigoConsultado);
				HabilitarEntrada(false);
				consultarProducto();
				break;
		}
	}
	//METODOS  VOID SIN PARAMETOS
	void ajustarAnchoColumnas(){
		TableColumnModel tcm=tblProducto.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna( 8));  // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(18));  // nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10));  // apellido
	}
	void listar(){
		Producto x;
		modelo.setRowCount(0);
			for(int i=0;i<ap.tamaño();i++){
				x=ap.obtener(i);
				Object[]fila={x.getCodigoProducto(),
							x.getDetalle(),
							x.getPrecioUnitario(),
							x.getStock()};
				modelo.addRow(fila);
	}
	}
	
	
	void adicionarProducto(){
		btnOk.setEnabled(true);
		int codigoProducto=leerCodigoProducto();
		int stock=leerStock();
		double preciounitario=leerPrecioUnitario();
		String detalle=leerDetalle();
		Producto nuevo=new Producto(codigoProducto, detalle, preciounitario, stock);
			ap.adicionar(nuevo);
		listar();
		txtCodigo.setText(""+ap.codigoCorrelativo());
		txtDetalle.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
	}
	void consultarProducto(){
		btnOk.setEnabled(true);
		try{
			Producto x=ap.buscar(leerCodigoProducto());
			if(x!=null){
				codigoConsultado=x.getCodigoProducto();
				txtDetalle.setText(x.getDetalle());
				txtPrecio.setText(""+x.getPrecioUnitario());
				txtStock.setText(""+x.getStock());
				txtCodigo.requestFocus();
			}else 
				error("El codigo"+ leerCodigoProducto()+"no existe",txtCodigo);
		}catch(Exception e){
			error("INGRESE CODIGO A CONSULTAR",txtCodigo);
		}
	}
	void modificarProducto(){
		btnOk.setEnabled(true);
		Producto x=ap.buscar(codigoConsultado);
		String detalle=leerDetalle();
		double preciounitario=leerPrecioUnitario();
		int stock=leerStock();
		x.setDetalle(detalle);
		x.setPrecioUnitario(preciounitario);
		x.setStock(stock);
		listar();
	}
	void eliminarProducto(){
		btnOk.setEnabled(true);
		int ok=confirmar("¿Desea eliminar el registro?");
			if(ok==0){
				Producto x=ap.buscar(codigoConsultado);
				ap.eliminar(x);
				listar();
				if(ap.tamaño()>0){
					codigoConsultado=ap.obtener(0).getCodigoProducto();
					txtCodigo.setText(""+codigoConsultado);
					consultarProducto();
				}
				else{
					codigoConsultado=ap.codigoCorrelativo();
					cboOperacion.setSelectedIndex(0);
				}
				
			}
	}
	
	
	//METODOS VOID CON PARAMETROS
	void HabilitarEntrada(boolean sino){
		txtDetalle.setEditable(sino);
		txtPrecio.setEditable(sino);
		txtStock.setEditable(sino);
	}
	void mensaje(String s){
		JOptionPane.showMessageDialog(this,s,"Informacion",0);
	}
	void error(String s,JTextField txt){
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
		}
	
	
	//METODOS QUE RETORNAN VALOR (SIN PARAMETRO)
	int leerCodigoProducto(){
		return Integer.parseInt(txtCodigo.getText().trim());
	}
	double leerPrecioUnitario(){
		return Double.parseDouble(txtPrecio.getText().trim());
	}
	String leerDetalle(){
		return txtDetalle.getText().trim();
	}
	int leerStock(){
		return Integer.parseInt(txtStock.getText().trim());
	}
	int leerPosOperacion(){
		return cboOperacion.getSelectedIndex();
	}
	//METODOS QUE  RETORNAN VALOR
	int anchoColumna(int porcentaje){
		return porcentaje*scrollPane.getWidth()/100;
	}
	String ajustar(double numero){
		return String.format("5.2",numero);
	}
	int confirmar(String s){
		return JOptionPane.showConfirmDialog(this,s,"Alerta",0,1,null);
	}
}

