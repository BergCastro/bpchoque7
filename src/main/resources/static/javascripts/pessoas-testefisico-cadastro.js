var Brewer = Brewer || {};

Brewer.PessoaTesteCadastro = (function() {
	
	function PessoaTesteCadastro() {
		
		this.botaoIncluir = $('#btn-incluir-pessoa')
		this.inputParticipante = $('#participante');
		
		this.url = this.botaoIncluir.data('url');
		
		
		
		this.containerMensagemErro = $('.js-mensagem-cadastro-prova');
	}
	
	PessoaTesteCadastro.prototype.iniciar = function() {
		
		this.botaoIncluir.on('click', onBotaoIncluirClick.bind(this));
		onAtualiza
	}
	
	
	
	
	
	function onBotaoIncluirClick() {
		var idParticipante = this.inputParticipante.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({id: idParticipante}),
			
			error: onErroSalvandoEstilo.bind(this),
			success: onEstiloSalvo.bind(this)
		});
		
		
	   
	}
	
	function onErroSalvandoEstilo(obj) {
		//var mensagemErro = obj.responseText;
		//this.containerMensagemErro.removeClass('hidden');
		//this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		//this.form.find('.form-group').addClass('has-error');
	}
	
	function onAtualizaPessoasIncluir(obj) {
		$("#pessoasIncluirBlock").load('/testesFisicos/atualizaPessoasIncluir');
	}
	
	function onAtualizaResultados(obj) {
		$("#listaDetalheTaf").load('/testesFisicos/atualizaResultadosTaf');
		$("#listaDetalheTheCdc").load('/testesFisicos/atualizaResultadosTheCdc');
	}
	
	function onAtualizaJavascript(obj) {
		$("#javascriptBloco").load('/testesFisicos/atualizaJavaScript');
	}
	
	function onEstiloSalvo(estilo) {
		
		
		
		this.inputParticipante.focus();
		
		onAtualizaPessoasIncluir();
		onAtualizaResultados();
		onAtualizaJavascript();
		
		
		
		//$.getScript('/javascripts/vendors/uikit.min.js');
		//$.getScript('/stylesheets/pesquisa/bootstrap-select.css');
		
		//$.getScript('/layout/javascripts/vendors.min.js');
		//$.getScript('/javascripts/pesquisa/bootstrap-select.js');
		//$.getScript('/javascripts/pessoas-testefisico-cadastro.js');
		
		
		
		//$("#blocoModalProva").load('/tiposTeste/atualizaModal');
		
		
		//this.modal.modal('hide');

	    
	    // Delegated events because we make a copy, and the copied button does not exist onDomReady
		 	
	    
		//$.getScript('/javascripts/dialogo-excluir-ajax.js');
	
		//$.getScript('/layout/javascripts/algaworks.min.js');
		
		
		/*var comboEstilo = $('#listaDetalhe');
		comboEstilo.append(
				           '<tr style="background:#ffb3b3;">'+
								'<td>'+estilo.nome+'</td>'+
								'<td>'+estilo.descricao+'.00</td>'+
								'<td style="text-transform: uppercase;">'+estilo.descricao+'</td>'+
								'<td class="text-center">'+
									 '<i class="glyphicon glyphicon-floppy-remove"></i>'+
									 
									
								'</td>'+
								'</tr>');
		comboEstilo.val(estilo.id);*/
		
		//location.reload();
	}
	
	return PessoaTesteCadastro;
	
}());




$(function() {
	var detalheCadastro = new Brewer.PessoaTesteCadastro();
	detalheCadastro.iniciar();
});





