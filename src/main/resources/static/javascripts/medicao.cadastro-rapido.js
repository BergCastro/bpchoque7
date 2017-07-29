var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function() {
	
	function EstiloCadastroRapido() {
		this.modal = $('#modalCadastroRapidoEstilo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputTorax = $('#torax');
		this.inputAbdominal = $('#abdominal');
		this.inputQuadril = $('#quadril');
		this.inputCintura = $('#cintura');
		this.inputBracoe = $('#bracoe');
		this.inputBracod = $('#bracod');
		this.inputCoxae = $('#coxae');
		this.inputCoxad = $('#coxad');
		
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	}
	
	EstiloCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeEstilo.focus();
	}
	
	function onModalClose() {
		this.inputTorax.val('');
		this.inputAbdominal.val('');
		this.inputQaudril.val('');
		this.inpuCintura.val('');
		this.inputBracoe.val('');
		this.inputBracod.val('');
		this.inputCoxae.val('');
		this.inputCoxad.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var torax = this.inputNomeEstilo.val().trim();
		var abdominal = this.inputNomeEstilo.val().trim();
		var cintura = this.inputNomeEstilo.val().trim();
		var quadril = this.inputNomeEstilo.val().trim();
		var bracoe = this.inputNomeEstilo.val().trim();
		var bracod = this.inputNomeEstilo.val().trim();
		var bracod = this.inputNomeEstilo.val().trim();
		var bracod = this.inputNomeEstilo.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeEstilo,
				}),
			}
			error: onErroSalvandoEstilo.bind(this),
			success: onEstiloSalvo.bind(this)
		});
	}
	
	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.id + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.id);
		this.modal.modal('hide');
	}
	
	return EstiloCadastroRapido;
	
}());

$(function() {
	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
	estiloCadastroRapido.iniciar();
});
