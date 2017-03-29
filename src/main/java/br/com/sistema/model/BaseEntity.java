package br.com.sistema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private ID id;
	/**
	 * Obtém o valor do atributo id
	 * @return the id
	 */
	public ID getId() {
		return id;
	}
	/**
	 * atribui o valor do atributo id
	 * @param id
	 */
	public void setId(ID id) {
		this.id = id;
	}


}
