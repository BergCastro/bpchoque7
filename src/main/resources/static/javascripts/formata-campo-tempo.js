var Brewer = Brewer || {};

Brewer.MaskTime = (function() {
	
	function MaskTime() {
		this.time = $('.js-time');
		//this.timeMinutos = $('.js-time-min');
		//this.inteiro = $('.inteiro');
	}
	
	MaskTime.prototype.enable = function() {

		this.time.mask("00''00");
		
		
	}
	
	return MaskTime;
	
}());

Brewer.MaskTimeMin = (function() {
	
	function MaskTimeMin() {
		this.inputTimeMin = $('.js-time-min');
	}
	
	MaskTimeMin.prototype.enable = function() {
		this.inputTimeMin.mask("00'00");
	}
	
	return MaskTimeMin;
	
}());

$(function() {
	var maskTime = new Brewer.MaskTime();
	maskTime.enable();
	
	var maskTimeMin = new Brewer.MaskTimeMin();
	maskTimeMin.enable();
	
	
});