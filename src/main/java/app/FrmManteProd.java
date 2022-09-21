package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tbtSalida;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 372, 414, 211);
		contentPane.add(scrollPane_1);
		
		tbtSalida = new JTable();
		scrollPane_1.setViewportView(tbtSalida);
		tbtSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Categoria");
		modelo.addColumn("Proveedor");
		
		llenaCombo();
	}

	void llenaCombo() {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();		

		// Combo1: obtener un listado de las categorías
		List<Categoria> lstCategoria = em.createQuery("select c from Categoria c", Categoria.class).getResultList();
		
		// Pasar el listado al objeto comboCategoria
		cboCategorias.addItem("Seleccione");
		for (Categoria c : lstCategoria) {
			cboCategorias.addItem(c.getIdcategoria() + " - " + c.getDescripcion());
		}
		
		// Combo2: obtener un listado de los proveedores
		List<Proveedor> lstProveedor = em.createQuery("select pv from Proveedor pv", Proveedor.class).getResultList();
		em.close();
		
		//pasar el listado de cboProveedores
		cboProveedores.addItem("Seleccione");
		for (Proveedor pv : lstProveedor) {
			cboProveedores.addItem(pv.getIdproveedor() + " - " + pv.getNombre_rs());
		}
	}

	void registrar() {
		// leer los campos
		String codigo = leerCodigo();
		String nombre = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int idcategoria = cboCategorias.getSelectedIndex();
		int idproveedor = cboProveedores.getSelectedIndex();
		int estado = 1; //true
		
		// validaciones
		if (codigo == null) {
			//JOptionPane.showMessageDialog(null, "Es incorrecto");
			return;
		}
		
		// Obj nuevo producto
		Producto p = new Producto();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setStk_prod(stock);
		p.setPre_prod(precio);
		p.setIdcategoria(idcategoria);
		p.setIdproveedor(idproveedor);
		p.setEst_prod(estado);
		
		// Guardar -> registra en la tabla
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//transaccion
		try {
			em.getTransaction().begin();
			
			em.persist(p);
			
			em.getTransaction().commit();
			
			aviso("Registro OK", "Aviso Sistema",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			aviso("Error al registrar", "Aviso Sistema",JOptionPane.ERROR_MESSAGE);
		}
		
		em.close();
		
	}
	
	String leerCodigo() {
		if(!txtCodigo.getText().matches("[Pp][0-9]{4}")) {
			return null;
		}
		return txtCodigo.getText();
	}
	
	void aviso(String msg, String tit, int icono) {
		JOptionPane.showMessageDialog(this,msg, tit,icono);
	}

	void listado() {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();	
		
		// Combo1: obtener un listado de las categorías
		List<Producto> lstProducto = em.createQuery("select p from Producto p", Producto.class).getResultList();
		
		System.out.println("Listado de Usuarios");
		
		//mostrar el listado en el txtArea
		for (Producto p : lstProducto) {
			//muestra en el txtArea
			imprimir("Id Producto...: " + p.getId_prod());
			imprimir("Nombre...: " + p.getDes_prod());
			imprimir("Categoria...: " + p.getIdcategoria() + "-" + p.getCategoria().getDescripcion());
			imprimir("Proveedor...: " + p.getIdproveedor() + "-" + p.getProveedor().getNombre_rs());
			imprimir("---------------------------------------------");
			//muestra en la tabla
			Object datos[] = {p.getId_prod(), p.getDes_prod(), 
					p.getIdcategoria() + "-" + p.getCategoria().getDescripcion(),
					p.getIdproveedor() + "-" + p.getProveedor().getNombre_rs()};
			modelo.addRow(datos);
		}
	
		
		em.close();
		
	}
	
	// Método para imprimir
		void imprimir(String s) {
			txtSalida.append(s + "\n");
		}
	
	void buscar() {
		// Leer el código
		String codigo = leerCodigo();
		
		// Buscar un producto con el código indicado
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		Producto p = em.find(Producto.class, codigo);
		// Si existe, muestra en los campos los valores correspondientes
		if (p != null) {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText(p.getStk_prod()+"");
		}else {
			aviso("Codigo de producto no existe", "Aviso Sistema",JOptionPane.ERROR_MESSAGE);
		}
		em.close();
		
		// Sino existe muestra mensaje de error
		
	}
}