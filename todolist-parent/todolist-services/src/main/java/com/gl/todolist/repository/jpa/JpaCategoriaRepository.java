package com.gl.todolist.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.todolist.domain.Categoria;
import com.gl.todolist.repository.CategoriaRepository;

@Repository
@Transactional
public class JpaCategoriaRepository implements CategoriaRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Categoria find(Long id) throws EntityNotFoundException {
		Categoria c = em.find(Categoria.class, id);
		if(c == null){
			throw new EntityNotFoundException();
		}else return c;
	}

	@Override
	public Categoria saveCategoria(Categoria categoria) {
		return em.merge(categoria);
	}

	@Override
	public Categoria updateCategoria(Categoria categoria) {
		return em.merge(categoria);
	}

	@Override
	public void deleteCategoria(Long id) throws EntityNotFoundException {
		try {
			em.createQuery("DELETE categoria where id =:categoriaId").setParameter("categoriaId", id).executeUpdate();
		}catch(Exception e){
			throw new EntityNotFoundException(e.getMessage());
		}
		
	}

	@Override
	public List<Categoria> listAll() {
		Query q = em.createQuery("FROM categoria");
		List<Categoria> resultList = q.getResultList();
		return resultList;
	}

}
