package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity //esto hace que se convierta nuestra clase en una entidad
@Data //Anotacion que automaticamente creará los get, set, constructor vacio, toString y otros métodos
@Table(name = "tb_tipos")
public class Tipo {
	@Id
	private int idtipo ;
	private String descripcion;
}
