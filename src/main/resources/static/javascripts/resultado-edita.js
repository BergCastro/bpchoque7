var Bpchoque = Bpchoque || {};

Bpchoque.ResultadoEditar = (function() {
	
	function ResultadoEditar() {
		this.editarBtn = $('.btn-editar')
		this.modal = $('#modalEditaResultado');
		this.botaoSalvar = this.modal
				.find('.js-modal-cadastro-resultado-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputParticipante = $('#participante');
		this.inputQtdProvas = $('#qtdProvas');
		this.idResultado;

		this.containerMensagemErro = $('.js-mensagem-cadastro-detalhe');
	}
	
	ResultadoEditar.prototype.iniciar = function() {
		this.editarBtn.on('click', onEditarClicado.bind(this));
		this.form.on('submit', function(event) {
			event.preventDefault()
		});
		this.modal.on('show.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
		
		
	}
	
	function onEditarClicado(evento) {
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		idResultado = botaoClicado.data('resultado');
		//var objeto = botaoClicado.data('objeto');
		
		console.log(idResultado);
		$("#editaBlock").load('/testesFisicos/atualizaEditaResultado/'+idResultado);
		this.modal.modal('show');
		
	}
	function onModalShow() {
		
		
		
		
	}

	function onModalClose() {
		//this.inputParticipante.val('');
		
		
		    // Also clear loaded content, otherwise it would flash before new one is loaded.
		
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	function onBotaoSalvarClick() {
		var participante = this.inputParticipante.val().trim();

		var qtdProvas = this.inputQtdProvas.val();

		

		var inputValorProvas = [];
		var inputIdProvas = [];
		
		
		$('.input-prova').each(function() {
			
			var input = $(this).val();
			inputValorProvas.push(input);
		});
		$('.input-id-prova').each(function() {
			inputIdProvas.push($(this).val());
		});
		

		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				pessoa : participante,
				provas : inputIdProvas,
				valores : inputValorProvas
			}),

			error : onErroSalvandoEstilo.bind(this),
			success : onEstiloSalvo.bind(this)
		});
	}
	
	function onExcluirConfirmado(url) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this),
			complete: atualizaScript.bind(this)
		});
	}
	
	function onExcluidoSucesso() {
		//$("#tabelaProvasBlock").load('/tiposTeste/atualizaProvas');
		//$.getScript('/javascripts/dialogo-excluir-ajax.js');
		/*var urlAtual = window.location.href;
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
		
		window.location = novaUrl;*/
		//$("#resultsBlock").load('/testesFisicos/resultados');
		//$("#javascriptBloco").load('/testesFisicos/atualizaJavaScript');
		location.reload();
		
	}
	
	function atualizaScript() {
		
		
		
		
		/*$.getScript('/layout/javascripts/algaworks.min.js');
		$.getScript('/javascripts/vendors/jquery.masknumber.min.js');
		$.getScript('/javascripts/vendors/jquery.mask.min.js');
		$.getScript('/javascripts/vendors/bootstrap-datepicker.min.js');
		//$.getScript('/javascripts/vendors/bootstrap-datepicker.pt-BR.min.js');
		//$.getScript('/javascripts/vendors/numeral.min.js');
		//$.getScript('/javascripts/vendors/pt-br.min.js');
		
		$.getScript('/javascripts/Bpchoque.js');
		
		$.getScript('/javascripts/Bpchoque.dialogo-excluir.js');
		
		$.getScript('/javascripts/vendors/uikit.min.js');
		$.getScript('/javascripts/provas-tipoteste-cadastro.js');
		$.getScript('/javascripts/resultado-teste-cadastro.js');
		
		$.getScript('/javascripts/bpchoque.js');
		$.getScript('/javascripts/pesquisa/bootstrap-select.js');
		$.getScript('/javascripts/formata-campo-tempo.js');*/
	}
	
	function onErroExcluir(e) {
		console.log('ahahahah', e.responseText);
		swal('Oops!', e.responseText, 'error');
	}
	
	return ResultadoEditar;
	
}());

$(function() {
	var dialogo = new Bpchoque.ResultadoEditar();
	dialogo.iniciar();
});
