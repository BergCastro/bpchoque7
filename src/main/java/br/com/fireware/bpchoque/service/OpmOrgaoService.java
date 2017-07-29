package br.com.fireware.bpchoque.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.model.OpmOrgao;
import br.com.fireware.bpchoque.model.OpmOrgao.TipoOpm;

import br.com.fireware.bpchoque.repository.OpmOrgaoRepository;
import lombok.Data;

@Data
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class OpmOrgaoService {

	@Autowired
	private OpmOrgaoRepository repository;

	private List<OpmOrgao> opms;

	public OpmOrgaoService() {
		opms = new ArrayList<OpmOrgao>();
	}

	@Transactional(readOnly = false)
	public void save(OpmOrgao opmOrgao) {

		repository.save(opmOrgao);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(OpmOrgao opmOrgao) {

		repository.delete(opmOrgao);

	}

	public Iterable<OpmOrgao> findAll() {
		return repository.findAll();
	}
	
	public Iterable<OpmOrgao> findByTipoCivil() {
		return repository.findByTipo(TipoOpm.CIVIL);
	}
	
	public Iterable<OpmOrgao> findByTipoMilitar() {
		return repository.findByTipo(TipoOpm.MILITAR);
	}
	
	

	public OpmOrgao findOne(Long id) {

		return repository.findOne(id);

	}

}
