/*package pe.edu.ucsp.oms.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.ucsp.oms.domain.Administrator;
import pe.edu.ucsp.oms.repository.AdministratorDao;
import pe.edu.ucsp.oms.service.AdministratorService;

@Controller
@RequestMapping("/admi")
public class AdministratorController {
	final static Logger logger = Logger.getLogger(AdministratorController.class);

	@Autowired
	AdministratorService admiService;

	@Autowired
	AdministratorDao admiDao;

	public AdministratorController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Administrator handleGetAdministratorById(@PathVariable("id") Long id) {
		return admiDao.find(id);
	}

	@RequestMapping(method = RequestMethod.GET, params="email")
	public @ResponseBody
	List<Administrator> handleGetAdministratorByEmail(@RequestParam(value = "email", required = true) String email) {
		return admiDao.filterByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Administrator handleAddUsesr(@RequestBody Administrator admi) {
		admiDao.save(admi);
		return admi;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Administrator> handleListAll() {
		return admiDao.findAll();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Boolean handleRemoveByIds(@RequestParam("id") String[] ids) {
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting admis with ID: " + Arrays.deepToString(ids));
		}
		return true;
	}
}*/