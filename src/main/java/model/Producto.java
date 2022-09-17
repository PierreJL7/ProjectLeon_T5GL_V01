package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_productos")
public class Producto {
	@Id
	private String id_prod ;  // id_prod char(5) not null,
	private String des_prod; // des_prod varchar(45) not null,
	private int stk_prod; // stk_prod int,
	private double pre_prod; //pre_prod decimal(8,2) not null,
	private int idcategoria;
	private int est_prod; //est_prod boolean,
	private int idproveedor;
	
	@ManyToOne //Muchos a uno: este objeto guardará relación, osea es el join
	@JoinColumn(name = "idcategoria", insertable = false, updatable =  false) //ponemos insertable y updtable en false para especificar de que solamente se listara para no se inserta o modificara
	private Categoria categoria;
	
	@ManyToOne 
	@JoinColumn(name = "idproveedor", insertable = false, updatable =  false)
	private Proveedor proveedor;

	
}
