package br.com.fireware.bpchoque.model.def;

public enum EnumAptoInapto {
	
	APTO("Apto"), INAPTO("Inapto");

	
	private String descricao;

	EnumAptoInapto(String descricao) {
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}

	

}

