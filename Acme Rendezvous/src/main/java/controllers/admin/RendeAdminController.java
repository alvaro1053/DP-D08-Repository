
package controllers.admin;

import java.util.Collection;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.RendeService;
import controllers.AbstractController;
import domain.Admin;
import domain.Rende;

@Controller
@RequestMapping("rende/admin")
public class RendeAdminController extends AbstractController {

	@Autowired
	AdminService	adminService;

	@Autowired
	RendeService	rendeService;


	public RendeAdminController() {

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = this.createListModelAndView(null);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int rendeId) {
		ModelAndView result = null;
		Rende rende;

		rende = this.rendeService.findOne(rendeId);
		try {
			final String messageSuccessfullyDeleted = "rende.deleted";
			this.rendeService.deleteByAdmin(rende);
			result = this.createListModelAndView(messageSuccessfullyDeleted);
		} catch (final Throwable oops) {
			final String messageError = "rende.commit.error";
			result = this.createListModelAndView(messageError);
		}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendeId) {
		ModelAndView result;
		final String uri = "/admin";
		Rende rende = new Rende();
		final Boolean permisos = true;
		rende = this.rendeService.findOne(rendeId);

		result = new ModelAndView("rende/display");
		result.addObject("rende", rende);
		result.addObject("permisos", permisos);
		result.addObject("uri", uri);
		return result;
	}

	//Ancillary methods ---------------------------------------------

	protected ModelAndView createListModelAndView(final String message) {
		ModelAndView result;
		Collection<Rende> rendes;
		final String uri = "/admin";
		final Admin principal = this.adminService.findByPrincipal();
		final Boolean mayorDeEdad = true;
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		if (años < 18)
			rendes = this.rendeService.selectNotAdultRendes();
		else
			rendes = this.rendeService.findAll();

		result = new ModelAndView("rende/list");
		result.addObject("mayor", mayorDeEdad);
		result.addObject("principal", principal);
		result.addObject("rendes", rendes);
		result.addObject("message", message);
		result.addObject("uri", uri);
		return result;
	}
}
