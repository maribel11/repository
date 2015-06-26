package com.gl.todolist.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.gl.todolist.domain.Categoria;


public interface CategoriaServices {
	Categoria find(Long id) throws EntityNotFoundException;
	Categoria saveCategoria(Categoria categoria);
	Categoria updateCategoria(Categoria categoria);
	void deleteCategoria(Long id) throws EntityNotFoundException;
	List<Categoria> listAll();
}
