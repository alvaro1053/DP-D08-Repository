
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendeService;
import services.UserService;
import controllers.AbstractController;
import domain.Rende;
import domain.User;
import forms.RendeForm;

@Controller
@RequestMapping("/rende/user")
public class RendeUserController extends AbstractController {

	// Services

	@Autowired
	private RendeService	rendeService;

	@Autowired
	private UserService		userService;


	// Constructors

	public RendeUserController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rende> rendes;
		final String uri = "/user";
		final User principal = this.userService.findByPrincipal();
		Boolean mayorDeEdad = true;
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		if (años < 18) {
			mayorDeEdad = false;
			rendes = this.rendeService.selectNotAdultRendes();
		} else
			rendes = this.rendeService.findAll();

		result = new ModelAndView("rende/list");
		result.addObject("mayor", mayorDeEdad);
		result.addObject("principal", principal);
		result.addObject("rendes", rendes);
		result.addObject("uri", uri);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, params = {
		"filter"
	})
	public ModelAndView filter(@RequestParam final int filter) {
		final ModelAndView result;
		Collection<Rende> res = new ArrayList<Rende>();
		final User principal = this.userService.findByPrincipal();
		final String uri = "/user";
		final Boolean mayor = true;
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		if (filter == 0) {
			if (años < 18)
				res = this.rendeService.selectNotAdultRendes();
			else
				res = this.rendeService.findAll();
		} else if (filter == 1)
			res = this.rendeService.findByUserId(principal.getId());
		else if (filter == 2)
			res = this.rendeService.findRSVPSByUser();

		result = new ModelAndView("rende/list");
		result.addObject("mayor", mayor);
		result.addObject("principal", principal);
		result.addObject("uri", uri);
		result.addObject("rendes", res);

		return result;

	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendeId) {
		ModelAndView result;
		final String uri = "/user";
		Rende rende = new Rende();
		final User principal = this.userService.findByPrincipal();
		Boolean permisos = true;
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		rende = this.rendeService.findOne(rendeId);
		if ((años < 18) && rende.getAdultOnly() == true)
			permisos = false;

		result = new ModelAndView("rende/display");
		result.addObject("rende", rende);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("uri", uri);
		return result;
	}

	// Creation 
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		RendeForm rendeForm;
		this.userService.findByPrincipal();
		rendeForm = this.rendeService.create();
		final Boolean finalModeOption = false;
		result = this.createEditModelAndView(finalModeOption, rendeForm);

		result.addObject("permisos", true);
		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendeId) {
		ModelAndView result;
		User principal;
		Rende rende;
		Boolean permisos;
		RendeForm rendeForm;
		final Boolean finalModeOption = true;

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		rende = this.rendeService.findOne(rendeId);
		Assert.notNull(rende);
		Boolean isDeleted = rende.getIsDeleted();
		rendeForm = this.rendeService.reconstructForm(rende);

		permisos = principal.getId() == rende.getUser().getId();
		result = this.createEditModelAndView(finalModeOption, rendeForm);

		result.addObject("permisos", permisos);
		result.addObject("isDeleted", isDeleted);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RendeForm rendeForm, final BindingResult binding) {
		ModelAndView result;
		Rende rende;
		Boolean permisos;
		User principal;

		principal = this.userService.findByPrincipal();
		rende = this.rendeService.reconstruct(rendeForm, binding);

		permisos = principal.getId() == rende.getUser().getId();

		if (binding.hasErrors()) {
			rendeForm.setIsDraft(true);
			result = this.createEditModelAndView(rendeForm);
		} else
			try {
				this.rendeService.save(rende);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				rendeForm.setIsDraft(true);
				result = this.createEditModelAndView(rendeForm, "rende.commit.error");
			}

		result.addObject("permisos", permisos);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final RendeForm rendeForm, final BindingResult binding) {
		ModelAndView result;

		final Rende rende = this.rendeService.reconstruct(rendeForm, binding);

		try {
			this.rendeService.delete(rende);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rende, "rende.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/rsvp", method = RequestMethod.GET)
	public ModelAndView rsvp(@RequestParam final int rendeId) {
		ModelAndView result;
		Rende rende;
		User principal;
		Boolean successful;
		Boolean successfullyCancelled;

		rende = this.rendeService.findOne(rendeId);
		principal = this.userService.findByPrincipal();

		if (principal.getRendes().contains(rende)) {
			final Boolean error = true;
			result = this.createListModelAndView(null);
			result.addObject("youCantRSVP", error);
		}

		else if (rende.getAttendants().contains(principal)) {
			successfullyCancelled = true;
			this.rendeService.cancelRsvp(rende, principal);
			result = this.createListModelAndView(null);
			result.addObject("successfullyCancelled", successfullyCancelled);
		} else {
			successful = true;
			this.rendeService.rsvp(rende, principal);
			result = this.createListModelAndView(null);
			result.addObject("successful", successful);
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rende rende) {
		ModelAndView result;

		result = this.createEditModelAndView(rende, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Rende rende, final String message) {
		final ModelAndView result;
		final Collection<Rende> rendes = this.rendeService.findAll();
		domain.User principal;
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		Boolean permision = true;
		if (rende.getId() != 0 && (rende.getUser().getId() != principal.getId()))
			permision = false;

		result = new ModelAndView("rende/edit");
		result.addObject("permision", permision);
		result.addObject("rende", rende);
		result.addObject("rendes", rendes);
		result.addObject("message", message);
		return result;
	}

	// Ancillary methods Nuevos forms ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RendeForm rendeForm) {
		ModelAndView result;

		result = this.createEditModelAndView(rendeForm, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Boolean finalModeOption, final RendeForm rendeForm) {

		final ModelAndView result;
		final Collection<Rende> rendes = this.rendeService.findAll();

		result = new ModelAndView("rende/edit");
		result.addObject("rendeForm", rendeForm);
		result.addObject("rendes", rendes);
		result.addObject("finalModeOption", finalModeOption);

		return result;
	}

	protected ModelAndView createEditModelAndView(final RendeForm rendeForm, final String message) {
		final ModelAndView result;
		final Collection<Rende> rendes = this.rendeService.findAll();

		result = new ModelAndView("rende/edit");
		result.addObject("rendeForm", rendeForm);
		result.addObject("rendes", rendes);
		result.addObject("message", message);
		result.addObject("finalModeOption", true);

		return result;
	}

	protected ModelAndView createListModelAndView(final String message) {
		final ModelAndView result;
		Collection<Rende> rendes;
		final String uri = "/user";
		final User principal = this.userService.findByPrincipal();
		Boolean mayorDeEdad = false;
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		if (años < 18)
			mayorDeEdad = true;
		if (!mayorDeEdad)
			rendes = this.rendeService.findAll();
		else
			rendes = this.rendeService.selectNotAdultRendes();

		result = new ModelAndView("rende/list");
		result.addObject("mayor", mayorDeEdad);
		result.addObject("principal", principal);
		result.addObject("rendes", rendes);
		result.addObject("message", message);
		result.addObject("uri", uri);

		return result;
	}
}
