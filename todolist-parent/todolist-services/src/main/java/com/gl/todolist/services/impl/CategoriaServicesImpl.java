package com.gl.todolist.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.todolist.domain.Categoria;
import com.gl.todolist.repository.CategoriaRepository;
import com.gl.todolist.services.CategoriaServices;

@Service
public class CategoriaServicesImpl implements CategoriaServices{

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Override
	public Categoria find(Long id) throws EntityNotFoundException {
		return categoriaRepository.find(id);
	}

	@Override
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.saveCategoria(categoria);
	}

	@Override
	public Categoria updateCategoria(Categoria categoria) {
		return categoriaRepository.updateCategoria(categoria);
	}

	@Override
	public void deleteCategoria(Long id) throws EntityNotFoundException {
		categoriaRepository.deleteCategoria(id);	
		
	}

	@Override
	public List<Categoria> listAll() {
		return categoriaRepository.listAll();
	}

}
