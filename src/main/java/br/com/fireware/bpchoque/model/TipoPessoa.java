package br.com.fireware.bpchoque.model;

import br.com.fireware.bpchoque.model.validation.group.CnpjGroup;
import br.com.fireware.bpchoque.model.validation.group.CpfGroup;

public enum TipoPessoa {

	CIVIL("Civíl", "Identidade", "00000000000", CpfGroup.class) {
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	}, 
	
	MILITAR("Militar", "Matrícula", "000.000-0-0", CnpjGroup.class) {
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{1})(\\d{1})", "$1.$2.$3/$4-");
		}
	};

	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;

	TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}

	public abstract String formatar(String cpfOuCnpj);
	
	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGrupo() {
		return grupo;
	}
	
	public static String removerFormatacao(String cpfOuCnpj) {
		return cpfOuCnpj.replaceAll("\\.|-|/", "");
	}

}
