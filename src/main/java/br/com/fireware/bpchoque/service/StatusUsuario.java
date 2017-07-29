package br.com.fireware.bpchoque.service;

import br.com.fireware.bpchoque.repository.Usuarios;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void executar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByIdIn(codigos).forEach(u -> u.setAtivo(true));
		}
	},
	
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByIdIn(codigos).forEach(u -> u.setAtivo(false));
		}
	};
	
	public abstract void executar(Long[] codigos, Usuarios usuarios);
	
}
