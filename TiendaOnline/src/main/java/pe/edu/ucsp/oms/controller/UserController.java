package pe.edu.ucsp.oms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.UserDao;

@Controller
@RequestMapping("/User")
public class UserController {

	@Inject
	UserDao userDao;

	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("User/list", "users", userDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("user", userDao.find(id));
		view.setViewName("User/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("user", userDao.find(id));
		view.setViewName("User/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("user", new User());
		view.setViewName("User/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
		if (user.getId() == null) {
			user.setBalance(0.0);
			user.setState(true);
			user.setPoints(0);
			userDao.save(user);
			status.setComplete();
		}
		else {
			userDao.update(user);
			status.setComplete();
		}
		return list();
	}
}
