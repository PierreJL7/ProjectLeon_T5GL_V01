package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	// Registrar los datos de un nuevo usuario
	public static void main(String[] args) {
		// Establecer conexion ->  con la unidad de persistencia
		// En pocas palabras este metodo nos permite conectarnos a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// Crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// Proceso -> registrar, actualizar, eliminar -> transacciones 
		// obtengo la transaccion y la inicializo
		em.getTransaction().begin();
		
		// objeto a grabar de nuestro constructor usuario.
		Usuario u = new Usuario(30, "Carola", "Del Barrio","CaroOlita","Carito","1960/10/15",1,1,null);
		
		em.persist(u);//el persist nos permite que objeto grabar.
		
		System.out.println("Grabacion Ok");
		
		// Confirmar la transaccion
		em.getTransaction().commit();
		
		// Cerrar la conexion
		em.close();
	}
} 
