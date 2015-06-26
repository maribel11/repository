package com.gl.todolist.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gl.todolist.domain.Producto;
import com.gl.todolist.services.ProductoServices;

@Controller
@RequestMapping("/producto")
public class ProductoController extends BaseController{

	public ProductoController() {
		super();
	}
	
	@Autowired
	ProductoServices productoServices;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto saveProducto(@RequestBody Producto producto, HttpSession session){
		return productoServices.saveProducto(producto);		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Producto updateProducto(@RequestBody Producto producto, HttpSession session){
		return productoServices.updateProducto(producto);		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void deleteProducto(@PathVariable Long id, HttpSession session){
		productoServices.deleteProducto(id);		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Producto findProducto(@PathVariable Long id){
		return productoServices.find(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public List<Producto> listAll(HttpSession session){
		return productoServices.listAll();
	}
	
}
