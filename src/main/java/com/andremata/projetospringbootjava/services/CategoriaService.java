package com.andremata.projetospringbootjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.repositories.CategoriaRepository;
import com.andremata.projetospringbootjava.services.exceptions.DataIntegrityException;
import com.andremata.projetospringbootjava.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria consultar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + " Tipo: " + categoria.getClass().getName()));
	}
	
	public List<Categoria> consultar() {
		return repository.findAll();
	}
	
	public Categoria incluir(Categoria categoria) {
		categoria.setId(null);
		
		return repository.save(categoria);
	}
	
	public Categoria alterar(Categoria categoria) {
		consultar(categoria.getId());
		
		return repository.save(categoria);
	}
	
	public void deletar(Integer id) {
		consultar(id);
		
		try {
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Esta categoria possui produtos associados e não pode ser exluída!");
		}
	}
	
	public Page<Categoria> paginar(Integer pagina, Integer linhas, String ordenacao, String direcao) {
		PageRequest paginacao = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordenacao);
		
		return repository.findAll(paginacao);
	}
}
