package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.model.Sexo;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTafEspecial;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.ResultadoTafEspecialRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResultadoTafEspecialService {

	@Autowired
	private ResultadoTafEspecialRepository repository;

	@Transactional(readOnly = false)
	public void save(ResultadoTafEspecial resultadoTeste) {

		repository.save(resultadoTeste);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(ResultadoTafEspecial resultadoTeste) {

		repository.delete(resultadoTeste);

	}

	public ResultadoTafEspecial findById(Long id) {

		return repository.findOne(id);

	}

	public List<ResultadoTafEspecial> findByTeste(TesteFisico testeFisico) {

		return repository.findByTeste(testeFisico);

	}
	
	

	public List<ResultadoTafEspecial> findByPessoa(PessoaDef pessoa) {

		return repository.findByPessoa(pessoa);

	}

	public List<ResultadoTafEspecial> findAll() {
		return repository.findAll();
	}
	
	
	public Double calculaFlexaoBarraTaf(String valor, Integer idade) {
		Integer idadeInicial = 20;
		Integer idadeFinal = 54;
		Integer intervaloIdade = 5;

		Double referenciaInicialMasc = 1.00;
		Double referenciaFinalMasc = 12.00;

		Double intervaloReferencia = 1.00;
		Double valorFormatado = Double.parseDouble(valor);
		Integer faixaInicioPontuacao = 3;
		Double auxResultado = 10.00;
		Double auxReferencia = 3.00;
		Integer auxIdade = idadeInicial;
		Double auxMaxRefIdade = referenciaFinalMasc;
		Double resultado = 0.00;

		Boolean paraLoop = false;

		for (int j = 0; j < 7; j++) {
			if (paraLoop) {
				break;
			}
			for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
					/ intervaloReferencia; i++) {

				if (valor.equals("0")) {
					resultado = 0.00;
					paraLoop = true;
					break;
				} else if (valorFormatado >= referenciaFinalMasc) {
					resultado = 100.00;
					paraLoop = true;
					break;

				} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

					if (valorFormatado < auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado >= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}

				} else if (idade < idadeInicial) {
					if (valorFormatado < auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado >= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}
				}

				auxResultado += 10;
				auxReferencia += intervaloReferencia;

			}
			auxMaxRefIdade -= intervaloReferencia;
			auxIdade += intervaloIdade;
			auxResultado = auxResultado - 100;
			auxReferencia -= 11;
		}
		return resultado;
	}

	public Double calculaFlexaoSoloTaf(Sexo sexo, Integer idade, String valor) {
		Integer idadeInicial = 20;
		Integer idadeFinal = 54;
		Integer intervaloIdade = 5;
		Double referenciaInicialMasc = 14.00;
		Double referenciaFinalMasc = 45.00;
		Double referenciaInicialFem = 18.00;
		Double referenciaFinalFem = 41.00;
		Double intervaloReferencia = 2.00;
		Double valorFormatado = Double.parseDouble(valor);

		Integer faixaInicioPontuacao = 7;
		Double auxResultado = 10.00;
		Double auxReferencia = 26.00;
		Integer auxIdade = idadeInicial;
		Double auxMaxRefIdade = referenciaFinalMasc;
		Double resultado = 0.00;

		Boolean paraLoop = false;
		if (sexo == Sexo.MASCULINO) {
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valorFormatado >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado >= auxReferencia
								&& valorFormatado < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}
		} else {
			if (valorFormatado < 14) {
				resultado = 0.00;

			} else if (valorFormatado >= 40) {
				resultado = 100.00;
			} else if (valorFormatado >= 18 && valorFormatado <= 40) {
				referenciaFinalFem = 41.00;
				referenciaInicialFem = 18.00;
				intervaloReferencia = 2.00;
				faixaInicioPontuacao = 3;
				auxReferencia = 22.00;
				auxMaxRefIdade = referenciaFinalFem;

				for (int j = 0; j < 7; j++) {
					if (paraLoop) {
						break;
					}
					for (int i = faixaInicioPontuacao; i <= (referenciaFinalFem - (referenciaInicialFem - 1))
							/ intervaloReferencia; i++) {

						System.out.println("Valor: " + valor);
						System.out.println("Idade: " + idade);
						System.out.println("FaixaIdade: " + auxIdade + "-" + (auxIdade + intervaloIdade));
						System.out.println("AuxReferencia: " + auxReferencia);
						System.out.println("AuxResultado: " + auxResultado);
						System.out.println("AuxMaxREferencia: " + auxMaxRefIdade);

						if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
							resultado = 0.00;
							paraLoop = true;
							break;
						} else if (valorFormatado >= referenciaFinalFem) {
							resultado = 100.00;
							paraLoop = true;
							break;

						} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

							if (valorFormatado < auxReferencia) {
								resultado = 0.00;
								paraLoop = true;
								break;

							} else if ((valorFormatado >= auxReferencia
									&& valorFormatado < auxReferencia + intervaloReferencia)) {
								System.out.println("Entrou no if correto");
								resultado = auxResultado;
								paraLoop = true;
								break;
							} else if (valorFormatado >= auxMaxRefIdade) {
								resultado = 100.00;
								paraLoop = true;
								break;
							}

						} else if (idade < idadeInicial) {
							if (valorFormatado < auxReferencia) {
								resultado = 0.00;
								paraLoop = true;
								break;

							} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
								resultado = auxResultado;
								paraLoop = true;
								break;
							} else if (valorFormatado >= auxMaxRefIdade) {
								resultado = 100.00;
								paraLoop = true;
								break;
							}
						}

						auxResultado += 10;
						auxReferencia += intervaloReferencia;

					}
					auxMaxRefIdade -= intervaloReferencia;
					auxIdade += intervaloIdade;
					auxResultado = auxResultado - 100;
					auxReferencia -= 11 * intervaloReferencia;
				}

			} else if (valorFormatado == 14) {
				if (idade >= 50) {
					resultado = 10.00;
				} else {
					resultado = 0.00;
				}

			} else if (valorFormatado == 15) {
				if (idade >= 45) {
					if (idade >= 50) {
						resultado = 20.00;
					} else {
						resultado = 10.00;
					}
				} else {
					resultado = 0.00;
				}
			} else if (valorFormatado == 16) {
				if (idade >= 40) {
					if (idade >= 50) {
						resultado = 30.00;
					} else if (idade >= 45) {
						resultado = 20.00;
					} else {
						resultado = 10.00;
					}
				} else {
					resultado = 0.00;
				}
			} else if (valorFormatado == 17) {
				if (idade >= 35) {
					if (idade >= 50) {
						resultado = 40.00;
					} else if (idade >= 45) {
						resultado = 30.00;
					} else if (idade >= 40) {
						resultado = 20.00;
					} else {
						resultado = 10.00;
					}
				} else {
					resultado = 0.00;
				}
			}

		}

		return resultado;
	}
	
	public Double calculaAbdominal(String valor, Sexo sexo, Integer idade){
		Integer idadeInicial = 20;
		Integer idadeFinal = 54;
		Integer intervaloIdade = 5;
		Double referenciaInicialMasc = 18.00;
		Double referenciaFinalMasc = 49.00;
		Double referenciaInicialFem = 14.00;
		Double referenciaFinalFem = 45.00;
		Double intervaloReferencia = 2.00;
		Double valorFormatado = Double.parseDouble(valor);
		Integer faixaInicioPontuacao = 7;
		Double auxResultado = 10.00;
		Double auxReferencia = 30.00;
		Integer auxIdade = idadeInicial;
		Double auxMaxRefIdade = referenciaFinalMasc;
		Double referenciaInicial = referenciaInicialMasc;
		Double referenciaFinal = referenciaFinalMasc;
		Double resultado = 0.00;
		if (sexo == Sexo.FEMININO) {
			referenciaInicial = referenciaInicialFem;
			referenciaFinal = referenciaFinalFem;
			auxReferencia = 26.00;
			auxMaxRefIdade = referenciaFinalFem;
		}
		Boolean paraLoop = false;
		for (int j = 0; j < 7; j++) {
			if (paraLoop) {
				break;
			}
			for (int i = faixaInicioPontuacao; i <= (referenciaFinal - (referenciaInicial - 1))
					/ intervaloReferencia; i++) {
				/*
				 * System.out.println("Valor: " + valor);
				 * System.out.println("Idade: " + idade);
				 * System.out.println("FaixaIdade: " +
				 * auxIdade+"-"+(auxIdade+intervaloIdade) );
				 * System.out.println("AuxReferencia: " + auxReferencia);
				 * System.out.println("AuxResultado: " + auxResultado);
				 * System.out.println("AuxMaxREferencia: " +
				 * auxMaxRefIdade);
				 */
				if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
					resultado = 0.00;
					paraLoop = true;
					break;
				} else if (valorFormatado >= referenciaFinal) {
					resultado = 100.00;
					paraLoop = true;
					break;

				} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

					if (valorFormatado < auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado >= auxReferencia
							&& valorFormatado < auxReferencia + intervaloReferencia)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado >= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}

				} else if (idade < idadeInicial) {
					if (valorFormatado < auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado >= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}
				}

				auxResultado += 10;
				auxReferencia += intervaloReferencia;

			}
			auxMaxRefIdade -= intervaloReferencia;
			auxIdade += intervaloIdade;
			auxResultado = auxResultado - 100;
			auxReferencia -= 11 * intervaloReferencia;
		}
		return resultado;
	}
	public Double calculaCorrida12min(String valor, Sexo sexo, Integer idade){
		Integer idadeInicial = 20;
		Integer idadeFinal = 54;
		Integer intervaloIdade = 5;
		Double referenciaInicialMasc = 1400.00;
		Double referenciaFinalMasc = 2999.00;
		Double referenciaInicialFem = 1200.00;
		Double referenciaFinalFem = 2799.00;
		Double intervaloReferencia = 100.00;
		Double valorFormatado = Double.parseDouble(valor);
		Integer faixaInicioPontuacao = 7;
		Double auxResultado = 10.00;
		Double auxReferencia = 2000.00;
		Integer auxIdade = idadeInicial;
		Double auxMaxRefIdade = referenciaFinalMasc;
		Double referenciaInicial = referenciaInicialMasc;
		Double referenciaFinal = referenciaFinalMasc;
		Double resultado = 0.00;
		if (sexo == Sexo.FEMININO) {
			referenciaInicial = referenciaInicialFem;
			referenciaFinal = referenciaFinalFem;
			auxReferencia = 1800.00;
			auxMaxRefIdade = referenciaFinalFem;
		}
		Boolean paraLoop = false;
		for (int j = 0; j < 7; j++) {
			if (paraLoop) {
				break;
			}
			for (int i = faixaInicioPontuacao; i <= (referenciaFinal - (referenciaInicial - 1))
					/ intervaloReferencia; i++) {
				/*
				 * System.out.println("Valor: " + valor);
				 * System.out.println("Idade: " + idade);
				 * System.out.println("FaixaIdade: " +
				 * auxIdade+"-"+(auxIdade+intervaloIdade) );
				 * System.out.println("AuxReferencia: " + auxReferencia);
				 * System.out.println("AuxResultado: " + auxResultado);
				 * System.out.println("AuxMaxREferencia: " +
				 * auxMaxRefIdade);
				 */
				if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
					resultado = 0.00;
					paraLoop = true;
					break;
				} else if (valorFormatado >= referenciaFinal) {
					resultado = 100.00;
					paraLoop = true;
					break;

				} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

					if (valorFormatado < auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado >= auxReferencia
							&& valorFormatado < auxReferencia + intervaloReferencia)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado >= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}

				} else if (idade < idadeInicial) {
					if (valorFormatado < auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado >= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}
				}

				auxResultado += 10;
				auxReferencia += intervaloReferencia;

			}
			auxMaxRefIdade -= intervaloReferencia;
			auxIdade += intervaloIdade;
			auxResultado = auxResultado - 100;
			auxReferencia -= 11 * intervaloReferencia;
		}
		return resultado;
	}
	
	public Double calculaCorrida50m(String valor, Sexo sexo, Integer idade){
		Integer idadeInicial = 20;
		Integer idadeFinal = 54;
		Integer intervaloIdade = 5;
		Double referenciaInicialMasc = 1050.00;
		Double referenciaFinalMasc = 675.00;
		Double referenciaInicialFem = 1175.00;
		Double referenciaFinalFem = 800.00;
		Double intervaloReferencia = 25.00;
		Double valorFormatado = formataSegundos(valor);
		Integer faixaInicioPontuacao = 7;
		Double auxResultado = 10.00;
		Double auxReferencia = 900.00;
		Integer auxIdade = idadeInicial;
		Double auxMaxRefIdade = referenciaFinalMasc;
		Double referenciaInicial = referenciaInicialMasc;
		Double referenciaFinal = referenciaFinalMasc;
		Double resultado = 0.00;
		if (sexo == Sexo.FEMININO) {
			referenciaInicial = referenciaInicialFem;
			referenciaFinal = referenciaFinalFem;
			auxReferencia = 1025.00;
			auxMaxRefIdade = referenciaFinalFem;
		}
		Boolean paraLoop = false;
		for (int j = 0; j < 7; j++) {
			if (paraLoop) {
				break;
			}
			for (int i = faixaInicioPontuacao; i <= (referenciaInicial - (referenciaFinal - intervaloReferencia))
					/ intervaloReferencia; i++) {

				System.out.println("Valor: " + valor);
				System.out.println("Idade: " + idade);
				System.out.println("FaixaIdade: " + auxIdade + "-" + (auxIdade + intervaloIdade));
				System.out.println("AuxReferencia: " + auxReferencia);
				System.out.println("AuxResultado: " + auxResultado);
				System.out.println("AuxMaxREferencia: " + auxMaxRefIdade);

				if (valorFormatado <= referenciaFinal) {
					resultado = 100.00;
					if (valorFormatado == 0.00) {
						resultado = 0.00;
					}
					paraLoop = true;
					break;

				} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

					if (valorFormatado > auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado > auxReferencia - intervaloReferencia
							&& valorFormatado <= auxReferencia)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado <= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}

				} else if (idade < idadeInicial) {
					if (valorFormatado > auxReferencia) {
						resultado = 0.00;
						paraLoop = true;
						break;

					} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
						resultado = auxResultado;
						paraLoop = true;
						break;
					} else if (valorFormatado <= auxMaxRefIdade) {
						resultado = 100.00;
						paraLoop = true;
						break;
					}
				}

				auxResultado += 10;
				auxReferencia -= intervaloReferencia;

			}
			auxMaxRefIdade += intervaloReferencia;
			auxIdade += intervaloIdade;
			auxResultado = auxResultado - 100;
			auxReferencia += 11 * intervaloReferencia;
		}

		return resultado;
	}
	private Double formataSegundos(String valor) {
		Double resultado = 0.00;
		System.out.println(valor);
		if (valor.equals("00''00")) {
			resultado = 0.00;
		} else {
			resultado = Double.parseDouble(valor.replace("''", ""));
		}
		return resultado;
	}

}
