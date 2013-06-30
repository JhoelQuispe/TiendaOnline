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
@RequestMapping("/user")
public class UsuarioController {

	@Inject
	UserDao userDao;

	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("user/list", "users", userDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable int id) {
		ModelAndView view = new ModelAndView();
		view.addObject("user", userDao.find(id));
		view.setViewName("user/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable int id) {
		ModelAndView view = new ModelAndView();
		view.addObject("user", userDao.find(id));
		view.setViewName("user/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("user", new User());
		view.setViewName("user/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
		if (user.getId() == null) {
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
