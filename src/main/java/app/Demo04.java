package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	// Obtener toda la info de un usuario segun su codigo
	public static void main(String[] args) {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
						
		// Consulta -> objeto de usuario
		//em.find('CLASE A LLAMAR','CAMPO PRINCIPAL')
		Usuario u = em.find(Usuario.class, 20);
		
		//Si no se encuentra al usuario me muestra tal mensaje.
		if (u == null) {
			System.out.println("Usuario no encontrado");
		} else {
			System.out.println("Usuario encontrado");
			System.out.println(u);
		}
		
		
		em.close();
	}
}
