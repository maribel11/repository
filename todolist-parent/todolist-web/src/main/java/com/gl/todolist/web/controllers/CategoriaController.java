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

import com.gl.todolist.domain.Categoria;
import com.gl.todolist.services.CategoriaServices;

@Controller
@RequestMapping("/categoria")
public class CategoriaController extends BaseController{

	public CategoriaController() {
		super();
	}
	
	@Autowired
	CategoriaServices categoriaServices;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Categoria saveCategoria(@RequestBody Categoria categoria, HttpSession session){
		return categoriaServices.saveCategoria(categoria);		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Categoria updateCategoria(@RequestBody Categoria categoria, HttpSession session){
		return categoriaServices.updateCategoria(categoria);		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void deleteCategoria(@PathVariable Long id, HttpSession session){
		categoriaServices.deleteCategoria(id);		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Categoria findCategoria(@PathVariable Long id){
		return categoriaServices.find(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public List<Categoria> listAll(HttpSession session){
		return categoriaServices.listAll();
	}
}
