package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.Category;

public interface CategoryDao extends GenericDao<Category, Long> {

	Category findByname(String name);
	
	List<Category> filterByParent (String parent);

	Category findParent(Long id);
}
