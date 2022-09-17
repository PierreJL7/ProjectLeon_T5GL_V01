package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

//ANOTACIONES SON METADATAS, SIMBOLOS ESPECIALES
@Entity
@Table(name = "tb_usuarios")
@AllArgsConstructor //Crea los constructores internamente
@NoArgsConstructor //Crea un constructor vacío
@Setter //Crea set a tus atributos internamente
@Getter //Crea get a tus atributos internamente
@ToString 
//La clase usuario se convirtio en una entidad
public class Usuario {
	@Id
	@Column(name = "cod_usu")
	private int codigo;//cod_usua  int auto_increment,
	
	@Column(name = "nom_usua", length = 15)
	private String nombre;
	
	@Column(name = "ape_usua")
	private String apellido; //ape_usua varchar(25),
	
	@Column(name = "usr_usua", unique = true) //ya que es un atributo unico le damos al check unique.
	private String usuario; //usr_usua char(45) NOT NULL unique,
	
	@Column(name = "cla_usua")
	private String clave; //cla_usua char(100),
	
	@Column(name = "fna_usua")
	private String fchnac; //fna_usua  date  null,
	
	@Column(name = "idtipo")
	private int tipo; //idtipo int DEFAULT 2 CHECK (idtipo IN (1, 2)), 
	
	@Column(name = "est_usua")
	private int estado; //est_usua  int DEFAULT 1 check (est_usua IN (1,2)),
	
	@ManyToOne //Muchos a uno: este objeto guardará relación, osea es el join
	@JoinColumn(name = "idtipo", insertable = false, updatable =  false) //ponemos insertable y updtable en false para especificar de que solamente se listara para no se inserta o modificara
	private Tipo objTipo; // OJO: solo me servira para el listado y obtener la informacion del objeto
	
}
