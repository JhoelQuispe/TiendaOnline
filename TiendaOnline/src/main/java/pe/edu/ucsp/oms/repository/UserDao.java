package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.User;

public interface UserDao extends GenericDao<User, Long> {
	User findByEmail(String email);

	List<User> filterByEmail(String email);
	
	boolean existsUser(String username, String password);
}
