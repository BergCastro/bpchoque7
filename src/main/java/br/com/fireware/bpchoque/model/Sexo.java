package br.com.fireware.bpchoque.model;

public enum Sexo {

	MASCULINO("Masculino", "M"),
	FEMININO("Feminino", "F");
	
	private String descricao;
	private String abreviacao;
	
	Sexo(String descricao, String abreviacao) {
		this.descricao = descricao;
		this.abreviacao = abreviacao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getAbreviacao() {
		return abreviacao;
	}
	
	
}

