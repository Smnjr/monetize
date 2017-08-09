package br.com.sistema.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sistema.model.enums.TipoPerfil;

@Entity
@Table(name="PERFIL")
public class Perfil extends BaseEntity<Integer> {

	private static final long serialVersionUID = 4475404942519010107L;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PERFIL")
	private TipoPerfil tipoPerfil;

	/**
	 * @return the tipoPerfil
	 */

	public TipoPerfil getTipoPerfil() {
		return this.tipoPerfil;
	}

	/**
	 * @param tipoPerfil the tipoPerfil to set
	 */
	public void setTipoPerfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USUARIO_PERFIL", joinColumns = {
			@JoinColumn(name = "perfil_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "usuario_id", referencedColumnName = "id") })
	private List<Usuario> usuarioList;

}