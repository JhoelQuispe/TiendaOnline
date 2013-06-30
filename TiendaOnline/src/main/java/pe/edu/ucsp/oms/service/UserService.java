package pe.edu.ucsp.oms.service;

import pe.edu.ucsp.oms.domain.User;

public interface UserService {

	User findByEmail(String email);
}
