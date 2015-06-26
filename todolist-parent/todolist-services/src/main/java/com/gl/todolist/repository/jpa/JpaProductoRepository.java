package com.gl.todolist.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.todolist.domain.Producto;
import com.gl.todolist.repository.ProductoRepository;

@Repository
@Transactional
public class JpaProductoRepository implements ProductoRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Producto saveProducto(Producto producto) {
		return em.merge(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		return em.merge(producto);
	}

	@Override
	public void deleteProducto(Long id) throws EntityNotFoundException{
		try {
		em.createQuery("DELETE producto where id =:productoId").setParameter("productoId", id).executeUpdate();
		}catch(Exception e){
			throw new EntityNotFoundException(e.getMessage());
		}
		
	}

	@Override
	public List<Producto> listAll() {
		Query q = em.createQuery("FROM producto");
		List<Producto> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public Producto find(Long id)  throws EntityNotFoundException{
		Producto p = em.find(Producto.class, id);
		if(p == null){
			throw new EntityNotFoundException();
		}else return p;
	}

}
