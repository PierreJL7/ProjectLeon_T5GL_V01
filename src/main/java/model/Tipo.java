package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity //esto hace que se convierta nuestra clase en una entidad
@Data //Anotacion que automaticamente crear� los get, set, constructor vacio, toString y otros m�todos
@Table(name = "tb_tipos")
public class Tipo {
	@Id
	private int idtipo ;
	private String descripcion;
}
