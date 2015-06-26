package com.gl.todolist.repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.gl.todolist.domain.Producto;

public interface ProductoRepository {

	Producto find(Long id) throws EntityNotFoundException;
	Producto saveProducto(Producto producto);
	Producto updateProducto(Producto producto);
	void deleteProducto(Long id) throws EntityNotFoundException;
	List<Producto> listAll();
}
