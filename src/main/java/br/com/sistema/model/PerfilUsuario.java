package br.com.sistema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sistema.model.enums.TipoPerfil;

@Entity
@Table(name="USER_PROFILE")
public class PerfilUsuario {
     
    @Id
    @GeneratedValue
    private Integer id;
     
    @Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PERFIL", columnDefinition = "varchar(10)")
    private TipoPerfil tipoPerfil;

	/**
	 * @return the tipoPerfil
	 */
	public TipoPerfil getTipoPerfil() {
		return tipoPerfil;
	}

	/**
	 * @param tipoPerfil the tipoPerfil to set
	 */
	public void setTipoPerfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}
     
 
     
}