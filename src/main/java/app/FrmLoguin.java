package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLoguin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLoguin frame = new FrmLoguin();
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
	public FrmLoguin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(27, 38, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clave:");
		lblNewLabel_1.setBounds(27, 63, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(96, 35, 178, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(96, 60, 178, 20);
		contentPane.add(txtClave);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();
			}
		});
		btnIngresar.setBounds(326, 34, 89, 23);
		contentPane.add(btnIngresar);
	}
	
	void ingresar() {
		
		// leer los campos
			String usuario = leerUsuario();
			String clave = leerClave();
		// validaciones
			
			
		// Obtener un usuario según los campos de usuario y clave
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
			EntityManager em = fabrica.createEntityManager();
			
			//typedquery guardara temporalmente la ejecucion de esta consulta
			
			Usuario u;
			try {
				TypedQuery<Usuario> consulta = 
						em.createQuery("select u from Usuario u where u.usuario = :xusr and u.clave = :xpas", Usuario.class);
				consulta.setParameter("xusr", usuario);
				consulta.setParameter("xpas", clave);
				u = consulta.getSingleResult();
				aviso("Bienvenido","Mensaje del sistema",JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				u = null; //En caso de no encontrar un resultado lanza un Exception.
				aviso("Usuario no existe","Mensaje del sistema",JOptionPane.ERROR_MESSAGE);
			}
	}
	void aviso(String msg, String tit, int icono) {
		JOptionPane.showMessageDialog(this,msg, tit,icono);
	}


	private String leerClave() {
		// TODO Auto-generated method stub
		return String.valueOf(txtClave.getPassword());
	}

	private String leerUsuario() {
		// TODO Auto-generated method stub
		return txtUsuario.getText();
	}
}
