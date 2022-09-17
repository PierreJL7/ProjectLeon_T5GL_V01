package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_proveedor")
public class Proveedor {
	@Id
	private int idproveedor; // idproveedor int auto_increment,
	private String nombre_rs; //nombre_rs varchar(45) not null,
	private String telefono; //telefono varchar(10) not null,
	private String email; //email varchar(45) not null, 
}
