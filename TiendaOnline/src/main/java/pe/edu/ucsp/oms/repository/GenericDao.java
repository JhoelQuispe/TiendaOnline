package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.BaseEntity;

public interface GenericDao<T extends BaseEntity<PK>, PK extends Number>
{
	public void save(T entity);

	public void update(T entity);

	public void remove(T entity);

	public void removeById(PK id);

	public T find(PK id);

	public long count();
	
	public boolean exists(PK id);

	public List<T> findAll();
}
