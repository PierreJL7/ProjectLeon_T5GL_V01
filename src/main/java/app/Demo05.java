package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	// Eliminar un usuario pero obteniendo su informacion segun el codigo
	public static void main(String[] args) {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
				
		// Obj de usuario a eliminar
		Usuario u = em.find(Usuario.class, 30);
		u.setCodigo(30); //obtenemos el codigo.
	
		//Si no se encuentra al usuario me muestra tal mensaje.
		if (u == null) {
			System.out.println("Usuario no encontrado");
		} else {
			em.getTransaction().begin();
			em.remove(u);
			System.out.println("Usuario eliminado");
			em.getTransaction().commit();
		}
		
		em.close();
	}
}
