package br.com.sistema.model.enums;

public enum TipoPerfil {
	ROLE_USER(1, "USER"),
	ROLE_ADMIN (2, "ADMIN");
	
	
	Integer codigo;
	String perfil;

	TipoPerfil(Integer codigo, String perfil) {
		this.perfil = perfil;
		this.codigo = codigo;
	}
	
	
	public static TipoPerfil obterPorCodigo(Integer cod){
		TipoPerfil tipo = null;
		for(TipoPerfil t : TipoPerfil.values()){
			if(t.codigo.equals(cod)){
				tipo =  t;
			}
		}
		return tipo;
	}


	/**
	 * Obtém o valor do atributo codigo
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}


	/**
	 * Obtém o valor do atributo perfil
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}
}
