package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
	// Eliminar un usuario
	public static void main(String[] args) {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
				
		// obtengo la transaccion y la inicializo
		em.getTransaction().begin();
		
		// Obj de usuario a eliminar
		Usuario u = em.find(Usuario.class, 20);
		u.setCodigo(20); //obtenemos el codigo.
		
		em.remove(u);
		System.out.println("Usuario Eliminado");
		
		em.getTransaction().commit();
		
		em.close();
	}
}
