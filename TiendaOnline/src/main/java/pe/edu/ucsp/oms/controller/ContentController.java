package pe.edu.ucsp.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.Content;
import pe.edu.ucsp.oms.repository.ContentDao;

@Controller
@RequestMapping("/Content")
public class ContentController {
	
	@Autowired
	ContentDao contentDao;
	
	@RequestMapping("/list.html")
	public @ResponseBody
	ModelAndView list(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/Content/list");
		view.addObject("contents",contentDao.findAll());
		
		return view;
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("/Content/details");
		view.addObject("content", contentDao.find(id));
		
		return view;
	}
	

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("content", contentDao.find(id));
		view.setViewName("Content/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("content", new Content());
		view.setViewName("Content/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("content") Content content, BindingResult result, SessionStatus status) {
		if (content.getId() == null) {
			content.setSize(0);
			content.setTimesDownload(0);
			content.setContent("rutaContent");
			contentDao.save(content);
			status.setComplete();
		}
		else {
			System.out.println(content.toString());
			contentDao.update(content);
			status.setComplete();
		}
		return list();
	}
	
}
