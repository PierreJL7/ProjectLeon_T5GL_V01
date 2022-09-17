package app;

import java.util.List; //arraylist

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import model.Usuario;

public class Demo06 {
	// LISTADO DE TODOS LOS USUARIOS
	public static void main(String[] args) {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();		
		
		//proceso --> consulta -> select * from tb_usuarios
		//la u dentro del createQuery hace referencia al *, la cual se convertira en variable
		//puede ser cualquier caractér
		//no ponemos tb_usuarios, sino la clase que se convirtio en entidad la cual es Usuario.
		List<Usuario> lstUsuarios = em.createQuery("select u from Usuario u", Usuario.class).getResultList();
		
		System.out.println("Listado de Usuarios");
		for (Usuario us : lstUsuarios) {
			System.out.println("Codigo: " + us.getCodigo()); //getCodigo me retorna el codigo del usuario
			System.out.println("Nombre: " + us.getNombre() + " " +  us.getApellido());
			System.out.println("Tipo: " + us.getTipo() + " - " + us.getObjTipo().getDescripcion());
			System.out.println("--------------------------");
		}
		
		em.close();
	}
}
