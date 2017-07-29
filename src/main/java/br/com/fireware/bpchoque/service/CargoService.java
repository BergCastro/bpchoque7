package br.com.fireware.bpchoque.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.model.Cargo;
import br.com.fireware.bpchoque.repository.CargoRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	
	@Transactional(readOnly = false)
	public void save(Cargo cargo){
		
		repository.save(cargo);
		
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
		
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Cargo cargo){
		
		repository.delete(cargo);
		
	}
	
	
	
	public Cargo finById(Long id){
		
		return repository.findOne(id);
		
	}
	
	public Cargo findByNome(String nome){
		return repository.findByNome(nome);
	}
	
	
	public Iterable<Cargo> findAll(){
		return repository.findAll();
	}
	
	
	
	
}
