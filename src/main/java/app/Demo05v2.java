package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05v2 {
	//ELIMINAR LOGICAMENTE --> CAMBIANDO EL ESTADO DE 1 A 0
	//1 activado, 0 eliminado
	public static void main(String[] args) {
		// CONEXION
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
						
		// Obj de usuario a eliminar
		Usuario u = em.find(Usuario.class, 10);
		u.setCodigo(10); //obtenemos el codigo.
				
		//Si no se encuentra al usuario me muestra tal mensaje.
		if (u == null) {
			System.out.println("Usuario no encontrado");
		} else {
			//PROCESO --> CAMBIAR EL ESTADO
			u.setEstado(2);
			em.getTransaction().begin();
			em.merge(u);
			System.out.println("Usuario deshabilitado");
			em.getTransaction().commit();
		}
				
		em.close();
	}
}
