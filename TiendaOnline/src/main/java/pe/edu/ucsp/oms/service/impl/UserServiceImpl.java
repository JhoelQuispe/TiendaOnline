package pe.edu.ucsp.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.UserDao;
import pe.edu.ucsp.oms.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
