package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Proyecto extends JFrame implements ActionListener {
	private JLabel lblFondo;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_3;
	private JMenu mnNewMenu_4;
	private JMenuItem mntmSocio;
	private JMenuItem mntmProducto;
	private JMenuItem mntmBungalow;
	private JMenuItem mntmIngreso;
	private JMenuItem mntmConsum;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmHospedajes;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmHospedajesPagados;
	private JMenuItem mntmBoletas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proyecto frame = new Proyecto();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Proyecto() {
			
		setResizable(false);
		setTitle("HILTON HOTEL\u00AE ");
		setIconImage(new ImageIcon("imagenes/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(748, 560);
		this.setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Mantenimiento ");
		menuBar.add(mnNewMenu);
		
		mntmSocio = new JMenuItem("Socio");
		mntmSocio.addActionListener(this);
		mnNewMenu.add(mntmSocio);
		
		mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(this);
		mnNewMenu.add(mntmProducto);
		
		mntmBungalow = new JMenuItem("Bungalow");
		mntmBungalow.addActionListener(this);
		mnNewMenu.add(mntmBungalow);
		
		mnNewMenu_1 = new JMenu("Registro");
		menuBar.add(mnNewMenu_1);
		
		mntmIngreso = new JMenuItem("Ingreso");
		mntmIngreso.addActionListener(this);
		mnNewMenu_1.add(mntmIngreso);
		
		mntmConsum = new JMenuItem("Consumo");
		mntmConsum.addActionListener(this);
		mnNewMenu_1.add(mntmConsum);
		
		mntmNewMenuItem = new JMenuItem("Hospedaje");
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu_1.add(mntmNewMenuItem);
		
		mnNewMenu_2 = new JMenu("Pago");
		menuBar.add(mnNewMenu_2);
		
		mntmNewMenuItem_1 = new JMenuItem("Ingresos y Consumos");
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		mntmHospedajes = new JMenuItem("Hospedajes");
		mntmHospedajes.addActionListener(this);
		mnNewMenu_2.add(mntmHospedajes);
		
		mnNewMenu_3 = new JMenu("Reporte");
		menuBar.add(mnNewMenu_3);
		
		mntmNewMenuItem_2 = new JMenuItem("Ingresos  y Consumos pendientes");
		mntmNewMenuItem_2.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("Ingresos y Consumos Pagados");
		mntmNewMenuItem_3.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_4 = new JMenuItem("Hospedajes Pendientes");
		mntmNewMenuItem_4.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		mntmHospedajesPagados = new JMenuItem("Hospedajes Pagados");
		mntmHospedajesPagados.addActionListener(this);
		mnNewMenu_3.add(mntmHospedajesPagados);
		
		mnNewMenu_4 = new JMenu("Boleta");
		menuBar.add(mnNewMenu_4);
		
		mntmBoletas = new JMenuItem("Boletas");
		mntmBoletas.addActionListener(this);
		mnNewMenu_4.add(mntmBoletas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		lblFondo = new JLabel(new ImageIcon("imagenes/The-Muraka.jpg"));
		lblFondo.setBounds(0, 0, 750, 500);
		getContentPane().add(lblFondo);
		contentPane.setLayout(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmBoletas) {
			actionPerformedMntmBoletas(arg0);
		}
		if (arg0.getSource() == mntmHospedajesPagados) {
			actionPerformedMntmHospedajesPagados(arg0);
		}
		if (arg0.getSource() == mntmNewMenuItem_4) {
			actionPerformedMntmNewMenuItem_4(arg0);
		}
		if (arg0.getSource() == mntmNewMenuItem_3) {
			actionPerformedMntmNewMenuItem_3(arg0);
		}
		if (arg0.getSource() == mntmNewMenuItem_2) {
			actionPerformedMntmNewMenuItem_2(arg0);
		}
		if (arg0.getSource() == mntmHospedajes) {
			actionPerformedMntmHospedajes(arg0);
		}
		if (arg0.getSource() == mntmNewMenuItem_1) {
			actionPerformedMntmNewMenuItem_1(arg0);
		}
		if (arg0.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(arg0);
		}
		if (arg0.getSource() == mntmConsum) {
			actionPerformedMntmConsum(arg0);
		}
		if (arg0.getSource() == mntmIngreso) {
			actionPerformedMntmIngreso(arg0);
		}
		if (arg0.getSource() == mntmBungalow) {
			actionPerformedMntmBungalow(arg0);
		}
		if (arg0.getSource() == mntmProducto) {
			actionPerformedMntmProducto(arg0);
		}
		if (arg0.getSource() == mntmSocio) {
			actionPerformedMntmSocio(arg0);
		}
	}
	protected void actionPerformedMntmSocio(ActionEvent arg0) {
		JdSocio jds=new JdSocio();
		jds.setLocationRelativeTo(this);
		jds.setVisible(true);
	}
	protected void actionPerformedMntmProducto(ActionEvent arg0) {
		JdProducto jdp=new JdProducto();
		jdp.setLocationRelativeTo(this);
		jdp.setVisible(true);
	}
	protected void actionPerformedMntmBungalow(ActionEvent arg0) {
		JdBungalow jdb=new JdBungalow();
		jdb.setLocationRelativeTo(this);
		jdb.setVisible(true);
	}
	
	protected void actionPerformedMntmIngreso(ActionEvent arg0) {
		JdIngreso jdi=new JdIngreso();
		jdi.setLocationRelativeTo(this);
		jdi.setVisible(true);
	}
	protected void actionPerformedMntmConsum(ActionEvent arg0) {
		JdConsumo jdc=new JdConsumo();
		jdc.setLocationRelativeTo(this);
		jdc.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem(ActionEvent arg0) {
		JdHospedaje jdh=new JdHospedaje();
		jdh.setLocationRelativeTo(this);
		jdh.setVisible(true);
	}
	protected void actionPerformedMntmNewMenuItem_1(ActionEvent arg0) {
		JdIngresosyConsumos jdiyc=new JdIngresosyConsumos();
		jdiyc.setLocationRelativeTo(this);
		jdiyc.setVisible(true);
	}
	protected void actionPerformedMntmHospedajes(ActionEvent arg0) {
		JdHospedajes jdhs=new JdHospedajes();
		jdhs.setLocationRelativeTo(this);
		jdhs.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem_2(ActionEvent arg0) {
		JdIngresosyConsumosPendientes jdiycp=new JdIngresosyConsumosPendientes();
		jdiycp.setLocationRelativeTo(this);
		jdiycp.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem_3(ActionEvent arg0) {
		JdIngresosyConsumosPagados jdiycpa =new JdIngresosyConsumosPagados();
		jdiycpa.setLocationRelativeTo(this);
		jdiycpa.setVisible(true);
	}
	protected void actionPerformedMntmNewMenuItem_4(ActionEvent arg0) {
		JdHospedajesPendientes jdhp =new JdHospedajesPendientes();
		jdhp.setLocationRelativeTo(this);
		jdhp.setVisible(true);
	}
	protected void actionPerformedMntmHospedajesPagados(ActionEvent arg0) {
		JdHospedajesPagados jdhpa =new JdHospedajesPagados();
		jdhpa.setLocationRelativeTo(this);
		jdhpa.setVisible(true);
	}
	
	
	protected void actionPerformedMntmBoletas(ActionEvent arg0) {
		JdBoletas jdb =new JdBoletas();
		jdb.setLocationRelativeTo(this);
		jdb.setVisible(true);
	}
}
