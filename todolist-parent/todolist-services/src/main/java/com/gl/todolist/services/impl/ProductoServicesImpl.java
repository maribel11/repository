package com.gl.todolist.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.todolist.domain.Producto;
import com.gl.todolist.repository.ProductoRepository;
import com.gl.todolist.services.ProductoServices;

@Service
public class ProductoServicesImpl implements ProductoServices{

	@Autowired
	ProductoRepository productoRepository;
	
	
	@Override
	public Producto saveProducto(Producto producto) {
		return productoRepository.saveProducto(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		return productoRepository.updateProducto(producto);
	}

	@Override
	public void deleteProducto(Long id) throws EntityNotFoundException{
		productoRepository.deleteProducto(id);		
	}

	@Override
	public List<Producto> listAll() {
		return productoRepository.listAll();
	}

	@Override
	public Producto find(Long id) throws EntityNotFoundException{
		return productoRepository.find(id);
	}

}
