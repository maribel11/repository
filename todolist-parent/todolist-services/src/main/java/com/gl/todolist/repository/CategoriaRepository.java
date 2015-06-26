package com.gl.todolist.repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.gl.todolist.domain.Categoria;

public interface CategoriaRepository {

	Categoria find(Long id) throws EntityNotFoundException;
	Categoria saveCategoria(Categoria categoria);
	Categoria updateCategoria(Categoria categoria);
	void deleteCategoria(Long id) throws EntityNotFoundException;
	List<Categoria> listAll();
}
