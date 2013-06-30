package pe.edu.ucsp.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.repository.CategoryDao;

@Controller
@RequestMapping("/Category")
public class CategoryController{
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping("/list.html")
	public ModelAndView list(){
		return new ModelAndView("Category/list" ,"categories" , categoryDao.findAll());	
	}
	

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("Category/details");
		view.addObject("category", categoryDao.find(id));
		view.addObject("parent" , categoryDao.findParent(id));
		return view;
	}

}

