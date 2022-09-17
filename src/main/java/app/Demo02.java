package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	// Actualizar los datos del usuario ingresado
	public static void main(String[] args) {
		
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		// obtengo la transaccion y la inicializo
		em.getTransaction().begin();
		
		// Objeto a actualizar
		//Usuario u = new Usuario(10, "Juan", "Perez","jperez","123","2000/10/05",1,1);
		Usuario u = new Usuario(20, "Jose Carlos", "Perez Lopez", "jperez","j789","2000/10/05",1,1,null);
		
		em.merge(u); //merge busca --> actualiza si existe / REGISTRA SI ES QUE NO EXISTE
		
		System.out.println("USUARIO ACTUALIZADO");
		
		// Confirmar la transaccion
		em.getTransaction().commit();
		
		// Cerrar la conexion
		em.close();
	}
}
